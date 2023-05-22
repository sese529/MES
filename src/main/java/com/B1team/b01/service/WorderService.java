package com.B1team.b01.service;


import com.B1team.b01.dto.WorderDto;
import com.B1team.b01.dto.WplanDto;
import com.B1team.b01.entity.Worder;
import com.B1team.b01.repository.WorderRepository;
import com.B1team.b01.repository.WplanRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;


@RequiredArgsConstructor
@Service

public class WorderService {
    private final WplanRepository wplanRepository;
    private final WorderRepository worderRepository;



    @PersistenceContext
    private EntityManager entityManager;

    //문자열 시퀀스 메소드
    @Transactional
    public String makeStringId() {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT worder_seq.NEXTVAL FROM dual").getSingleResult();
        String id = "WORD" + sequenceValue;
        return id;

    }


    //
    public String seeWorder(String orderId) {

        //작업지시를 내릴 작업 계획이 있는지 조회(수주번호를 전송받아서)
        Optional<WplanDto> result = Optional.ofNullable(wplanRepository.findByOrderId(orderId));
        return orderId;
    }


    //작업지시 등록메소드
    public void doWorder(String orderId, WorderDto worderDto) {

        //작업지시를 내릴 작업 계획이 있는지 조회(수주번호를 전송받아서)
        Optional<WplanDto> result = Optional.ofNullable(wplanRepository.findByOrderId(orderId));

        //(작업계획 테이블에) 전송받은 수주번호에 대한 작업 계획이 있는지 확인: 없다면 등록하기
        Worder worder = new Worder();
        if (result.isPresent()) {

            worderDto.setId(makeStringId()); //문자열 시퀀스 추가
            worderDto.setProcessId(worderDto.getProcessId());
            worderDto.setWplanId(worderDto.getWplanId());
            worderDto.setFacilityId(worderDto.getFacilityId());
            worderDto.setStartDate(worderDto.getStartDate());
            worderDto.setFinishDate(worderDto.getFinishDate());

            worderRepository.save((worderDto.toEntity()));

        }
    }
}

