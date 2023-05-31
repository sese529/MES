package com.B1team.b01.service;

import com.B1team.b01.dto.DetailDto;
import com.B1team.b01.entity.Materials;
import com.B1team.b01.entity.PorderDetail;
import com.B1team.b01.repository.MaterialsRepository;
import com.B1team.b01.repository.PorderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PorderDetailService {
    private final PorderDetailRepository porderDetailRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    //문자열 시퀀스 메소드
    @Transactional
    public String generateId(String head, String seqName) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT " + seqName + ".NEXTVAL FROM dual").getSingleResult();
        String id = head + sequenceValue;
        return id;
    }

    public void addPorderDetail(String porderId
                                ,String mtrId
                                ,double cnt
                                ,long price) {
        PorderDetail dt = new PorderDetail();

        dt.setId(generateId("DET", "DETAIL_SEQ")); //////seq이름////////
        dt.setPorderId(porderId);
        dt.setMtrId(mtrId);
        dt.setDetailCnt(cnt);
        dt.setDetailPrice(price);

        porderDetailRepository.save(dt);
    }
}
