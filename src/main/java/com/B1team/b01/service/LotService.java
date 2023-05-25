package com.B1team.b01.service;


import com.B1team.b01.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;


@RequiredArgsConstructor
@Service
public class LotService {

    @Autowired
    private LotRepository lotRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //문자열 시퀀스 메소드
    public String makeStringId() {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT lot_seq.NEXTVAL FROM dual").getSingleResult();
        String id = "LOT" + sequenceValue;
        return id;
    }

    //작업지시에서 상태가 완료로 바뀌면(=작업완료시간이 현재시간 이전이면)


}
