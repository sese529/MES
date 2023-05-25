package com.B1team.b01.service;


import com.B1team.b01.dto.WorderDto;
import com.B1team.b01.dto.WplanDto;

import com.B1team.b01.entity.Wplan;
import com.B1team.b01.repository.RorderRepository;
import com.B1team.b01.repository.StockRepository;
import com.B1team.b01.repository.WplanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service

public class WplanService {
    @Autowired
    private final WplanRepository wplanRepository;
    private final RorderRepository rorderRepository;
    private final StockRepository stockRepository;

    @Autowired
    private MprocessService mprocessService;


    private final LocalDateTime ifNow = LocalDateTime.now();


    @PersistenceContext
    private EntityManager entityManager;


    //작성계획 등록메소드
    @Transactional
    public void createWplan(LocalDateTime materialReadyDate, String productId, long orderCnt, String orderId) {
        //orderDate : 수주 날짜(시작 날짜), productId : 품목id, orderCnt: 수주량(box)

        //수주고유번호로 계획이 세워져 있는지 확인  //wplan table 조회

        Optional<Wplan> confirmWplan = wplanRepository.findByPlanOrderId(orderId);

        //계획 세워져 있는게 없다면, 작업계획 등록하기
        //계획 시작일자 전에는 진행대기로 표시
        if (confirmWplan.isPresent()){
            System.out.println("세워져 있는계획이 있습니다");
        }else{
            WplanDto wplanDto = new WplanDto();
            List<WorderDto> worderDtos = mprocessService.calculateWorderDate(materialReadyDate, productId, orderCnt);


            wplanDto.setId(generateWplanId()); //문자열 시퀀스
            wplanDto.setCnt(productCnt(productId, orderId)); //생산 수량
            wplanDto.setStartDate(worderDtos.get(0).getStartDate()); //자재 준비 완료 시간
            wplanDto.setEndDate(worderDtos.get(worderDtos.size() - 1).getFinishDate()); //생산완료시간(=포장완료시간)
            wplanDto.setOrderId(orderId); //수주 고유번호
            wplanDto.setProductId(productId); // 품목명

            if (ifNow.isBefore(worderDtos.get(0).getStartDate())) {
                wplanDto.setState("진행대기");
            }else if(ifNow.isEqual(worderDtos.get(0).getStartDate()) || ifNow.isAfter(worderDtos.get(0).getStartDate())) {
                wplanDto.setState("진행중");
            }else{
                wplanDto.setState("완료");
            }//계획진행상태
            System.out.println("wplanDto----------" + wplanDto.toEntity());
            wplanRepository.save(wplanDto.toEntity());
        }
    }




    //문자열 시퀀스 메소드
    public String generateWplanId() {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT wplan_seq.NEXTVAL FROM dual").getSingleResult();
        String id = "WPLAN" + sequenceValue;
        return id;

    }

    //문자열 시퀀스 메소드
    public String generateId(String head, String seqName) {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT " + seqName + ".NEXTVAL FROM dual").getSingleResult();
        String id = head + sequenceValue;
        return id;

    }

    //생산필요량 구하기(수주량-재고량)
    public Long productCnt(String productId, String orderId) {

        Long orderCnt = rorderRepository.findByOrderCnt(orderId);//수주량
        Long stockCnt = stockRepository.findByStockCnt(productId);//재고량


        System.out.println(orderCnt);
        System.out.println(stockCnt);

        Long unit = Long.valueOf(productId.equals("p21") || productId.equals("p22") ? 30 : 25);
        Long orderCntUnit = orderCnt * unit;

        //수주량 - 재고량
        Long result = orderCntUnit - stockCnt ;
        System.out.println("생산필요량" + result);
        return result;
    }



}

