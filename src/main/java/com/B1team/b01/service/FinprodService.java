package com.B1team.b01.service;

import com.B1team.b01.dto.FinprodDto;

import com.B1team.b01.dto.ShipmentDto;
import com.B1team.b01.entity.Finprod;
import com.B1team.b01.entity.Rorder;

import com.B1team.b01.repository.FinprodRepository;
import com.B1team.b01.repository.RorderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FinprodService {

    private final FinprodRepository finprodRepository;
    private final RorderRepository rorderRepository;


    @PersistenceContext
    private EntityManager entityManager;



    //문자열 시퀀스 메소드
    public String makeStringId() {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT finprod_seq.NEXTVAL FROM dual").getSingleResult();
        String id = "FINP" + sequenceValue;
        return id;
    }

    //완제품 테이블 행 추가 메소드
    //완제품은 수주할 때 고유번호가 생성되어 있어야함(LOT번호 생성관련 제약때문에)
    public Finprod insertFinprod(String orderId){

        Optional<Rorder> rorderInfo = rorderRepository.findById(orderId);

        FinprodDto finprodDto = new FinprodDto();
            finprodDto.setId(makeStringId()); //완제품 고유번호
            finprodDto.setProductId(rorderInfo.get().getProductId());  //품목 고유번호
            finprodDto.setProduct(rorderInfo.get().getProductName());   //품목명
            finprodDto.setOrderId(orderId);    //수주 고유번호
            finprodDto.setEa(rorderInfo.get().getCnt()); //수량
            finprodDto.setDeadline(rorderInfo.get().getDeadline()); //납품예정일

        Finprod entity = finprodRepository.save(finprodDto.toEntity());
        return entity;
    }

    //출하관리 페이지 - 완제품에 거래처 추가하여 리스트 얻기
    //매개변수 String형을 레포지토리 메소드의 매개변수에 맞게 변경하기
    public List<ShipmentDto> getShipmentList(String customerName,
                                             String productName,
                                             String orderId,
                                             String start,
                                             String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDate = start == null || "".equals(start)? null : LocalDate.parse(start, formatter).atStartOfDay();
        LocalDateTime endDate = end == null || "".equals(end) ? null : LocalDate.parse(end, formatter).atTime(23, 59, 59);
        return finprodRepository.findShipmentsByConditions(customerName, productName, orderId, startDate, endDate, LocalDateTime.now());
    }
}
