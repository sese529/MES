package com.B1team.b01.service;


import com.B1team.b01.dto.WorderDto;
import com.B1team.b01.dto.WplanDto;
import com.B1team.b01.entity.Worder;
import com.B1team.b01.repository.RorderRepository;
import com.B1team.b01.repository.WorderRepository;
import com.B1team.b01.repository.WplanRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
@Service

public class WorderService {
    @Autowired
    private final WorderRepository worderRepository;
    private final RorderRepository rorderRepository;
    private final WplanRepository wplanRepository;

    @Autowired
    private MprocessService mprocessService;


    @PersistenceContext
    private EntityManager entityManager;

    //문자열 시퀀스 메소드
    public String makeStringId() {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT worder_seq.NEXTVAL FROM dual").getSingleResult();
        String id = "WORD" + sequenceValue;
        return id;
    }

    //(안쓰게된 메소드) 현재 시간에 작동중인 설비가 있는지 체크 //현재 작업지시 상태 조회
    public String checkWorder(String processId) {

        List<Worder> nowWorderTime = worderRepository.findByState(processId); //시작시간과 끝시간을 받아옴
        //현재 해당기기 공정 상태
        String result = "";

        for (int j = 0; j < nowWorderTime.size(); j++) {
            if (nowWorderTime.get(j).getStartDate().isAfter(LocalDateTime.now())) {
                result = "진행대기";
                return result; //현재시간이 시작시간보다 나중인 경우
            } else if (nowWorderTime.get(j).getFinishDate().isBefore(LocalDateTime.now())) { //현재시간이 종료시간보다 뒤인 경우 = 공정 종료 = 사용중인 설비가 없음
                result = "완료";
                return result;
            } else {
                result = "진행중";
                return result;
            }
        }
        return result;
    }


    //작업지시 조회
    public List<WorderDto> seeWorder(String orderId) {

        //작업지시를 내릴 '진행대기' 상태의 작업 계획이 있는지 조회(수주번호가 필요한?)
        Optional<WplanDto> result = Optional.ofNullable(wplanRepository.findByOrderId(orderId));
        System.out.println("조회" + result);
        return null;
    }


    //작업지시 등록메소드
    public List<WorderDto> doWorder(String orderId, LocalDateTime materialReadyDate, String productId) {

        //작업지시를 내릴 '진행대기' 상태의 작업 계획이 있는지 조회(수주번호가 필요한?)
        Optional<WplanDto> result = Optional.ofNullable(wplanRepository.findByOrderId(orderId));
        System.out.println("조회" + result);


        //있다면 작업지시 등록하기
        List<WorderDto> worderDtos = mprocessService.calculateWorderDate(materialReadyDate, productId);

        if (result.isPresent()) {

            for (int i = 0; i < worderDtos.size(); i++) {
                WorderDto worderDto = new WorderDto();
                worderDto.setId(makeStringId()); //작업지시 고유번호
                worderDto.setProcessId(worderDtos.get(i).getProcessId());//공정 고유번호
                worderDto.setWplanId(result.get().getId()); //작업계획코드 = 작업계획 고유번호
                worderDto.setProductId(result.get().getProductId());//품목명 고유번호
                worderDto.setStartDate(worderDtos.get(i).getStartDate()); //작업시작일자
                worderDto.setFinishDate(worderDtos.get(i).getFinishDate()); //작업종료일자
                worderDto.setStartDate(worderDtos.get(i).getStartDate()); //작업시작일자
                worderDto.setFinishDate(worderDtos.get(i).getFinishDate()); //작업종료일자
                worderDto.setFacilityId(worderDtos.get(i).getFacilityId()); //설비정보 고유번호

                worderRepository.save((worderDto.toEntity()));
            }
        }
        return worderDtos;
        }
    }

