package com.B1team.b01.Service;

import com.B1team.b01.dto.PinoutDto;
import com.B1team.b01.entity.Pinout;
import com.B1team.b01.entity.Wplan;
import com.B1team.b01.repository.PinoutRepository;
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
public class PinoutService {
    private final PinoutRepository pinoutRepository;
    private final WplanRepository wplanRepository;


    @PersistenceContext
    private EntityManager entityManager;

    //문자열 시퀀스 메소드
    @Transactional
    public String makeStringId() {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT pinout_seq.NEXTVAL FROM dual").getSingleResult();
        String id = "INOUT" + sequenceValue;
        return id;

    }


    //자재입출정보 등록메소드
    public String createInout(PinoutDto pinoutDto) {

        //해당 작업계획 확인
        String wplanId = new String(); //임의로 고유번호 받아오면
        Optional<Wplan> result = wplanRepository.findById(wplanId);

        //입출정보(출고) 등록하기
        if (result.isPresent()) {

            pinoutDto.setId(makeStringId()); //문자열 시퀀스 추가

            pinoutRepository.save((pinoutDto.toEntity()));

        }
        return null;
    }
}

