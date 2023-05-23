package com.B1team.b01.service;

import com.B1team.b01.dto.RorderDto;
import com.B1team.b01.dto.WplanDto;
import com.B1team.b01.entity.Rorder;
import com.B1team.b01.entity.Wplan;
import com.B1team.b01.repository.RorderRepository;
import com.B1team.b01.repository.WplanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Log4j2
public class WplanService {
    private final WplanRepository wplanRepository;
    private final RorderRepository rorderRepository;



    //작성계획 조회
    //rorder table 조회
/*    public Optional<Order> serchWplan(WplanDto dto) {
        RorderDto rorderDto = new RorderDto();
        Optional<Order> result = rorderRepository.findById(rorderDto.getId());
        return WplanRepository.(dto.toEntity());
    }*/

    @PersistenceContext
    private EntityManager entityManager;

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


    //작성계획 등록메소드
    public void createWplan(RorderDto rorderDto) {

        //rorder table 조회
        Optional<Rorder> result = rorderRepository.findById(rorderDto.getId());

        //등록하기
        if (result.isPresent()) {
            Rorder rorder = result.get();

            WplanDto wplanDto = new WplanDto();
            wplanDto.setId(generateWplanId()); //문자열 시퀀스
            wplanDto.setCnt(rorder.getCnt());
            wplanDto.setEndDate(LocalDateTime.now()); //시간 구해지면 넣기*
            wplanDto.setOrderId(rorder.getId());
            wplanDto.setProductId(rorder.getProductId());
            wplanDto.setStartDate(LocalDateTime.parse("2023-05-18T12:00:00")); //시간 구해지면 넣기*
            wplanDto.setState("진행중");

            wplanRepository.save(wplanDto.toEntity());

        }
    }
}

