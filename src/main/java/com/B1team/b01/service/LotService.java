package com.B1team.b01.service;


import com.B1team.b01.dto.LotDto;
import com.B1team.b01.dto.LotMakeNameDto;
import com.B1team.b01.entity.Finprod;
import com.B1team.b01.entity.Worder;
import com.B1team.b01.entity.Wplan;
import com.B1team.b01.repository.LotRepository;
import com.B1team.b01.repository.WorderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class LotService {

    @Autowired
    private LotRepository lotRepository;
    @Autowired
    private WorderRepository worderRepository;

    @Autowired
    private WorderService worderService;
    private Wplan wplan;

    @PersistenceContext
    private EntityManager entityManager;

    //문자열 시퀀스 메소드
    public String makeStringId() {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT lot_seq.NEXTVAL FROM dual").getSingleResult();
        String id = "LOT" + sequenceValue;
        return id;
    }

    //LOT 코드 생성규칙1
    public LotDto ruleProductName(String processId, String wplanId, String productId){
        List<Worder> someWorder = worderRepository.findByState(processId, wplanId); // 작업지시테이블의 특정 1행을 불러옴
        Finprod someFinprod = lotRepository.findByFinprodId(productId); // 작업지시테이블의 특정 1행을 불러옴

        //품목코드
        String productName = someWorder.get(0).getProductId();

        LotDto lotDto = new LotDto();


        LotMakeNameDto lotMakeNameDto= new LotMakeNameDto();
        switch (productName) {
            case "p21": lotMakeNameDto.setProduct("Y"); break;
            case "p22": lotMakeNameDto.setProduct("B"); break;
            case "p23": lotMakeNameDto.setProduct("S"); break;
            case "p24": lotMakeNameDto.setProduct("M"); break;
        }

        //공정과정
        String processName  = someWorder.get(0).getProcessId();
        lotMakeNameDto.setProcess(processName);

        //날짜
        LocalDateTime finishDate = someWorder.get(0).getFinishDate();

        String finishDateString = String.valueOf(finishDate);
        String newFinishDateString = finishDateString.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", ""); // ':' 문자 제거

        lotMakeNameDto.setDate(newFinishDateString);


        String productCode = lotMakeNameDto.getProduct();
        String processCode  = lotMakeNameDto.getProcess();
        String DateCode = lotMakeNameDto.getDate();

        //dto에 값 넣기
        lotDto.setProduct(productName);
        lotDto.setProcess(processName);
        lotDto.setDate(finishDate);
        lotDto.setCode(productCode + processCode + DateCode);
        lotDto.setId(makeStringId());
        lotDto.setWorderId(someWorder.get(0).getId());
        lotDto.setFinprodId(lotRepository.findByPlanOrderId(productId).getId()); //완제품 고유번호
        lotDto.setOrderId(String.valueOf(lotRepository.findByPlanOrderId(wplanId).getOrderId())); //수주 고유번호

        return lotDto;
    }

    //공정이 끝날 경우, LOT번호 추가하는 메소드
    public void createLotRecode(String processId, String wplanId, String orderId){
       String checkWorder = worderService.checkWorder(processId, wplanId); //공정고유번호를 체크해서 현재 가동상태가 완료인 것(=작업완료시간이 현재시간 이전이면)
            System.out.println("checkWorder");


        if(checkWorder == "완료"){
            LotDto result  = ruleProductName(processId, wplanId, orderId);
            lotRepository.save(result.toEntity());
        }else{
            System.out.println("등록실패");
        }
    }

}
