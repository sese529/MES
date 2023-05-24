package com.B1team.b01.dto;

import com.B1team.b01.entity.Rorder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RorderFormDto {
    public String id;
    public String customerId;
    public String customerName;
    public String productId;
    public String productName;
    public Long cnt;
    public Long price;
    public String state;
    public LocalDateTime date;
    public LocalDateTime changedate;
    public LocalDateTime deadline;

    private static ModelMapper modelMapper = new ModelMapper(); //엔티티랑 dto의 필드명이 같은 것끼리 매핑

//    @Autowired private EntityManager entityManager;

    //RorderFormDto를 Order 엔티티로 변환해주는 메소드
    public Rorder toEntity() {
//        this.setId(generateId("ROD", "order_seq"));
        return modelMapper.map(this, Rorder.class);
    }

    //문자열 시퀀스 메소드
//    @Transactional
//    public String generateId(String head, String seqName) {
//        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT " + seqName + ".NEXTVAL FROM dual").getSingleResult();
//        String id = head + sequenceValue;
//        return id;
//    }
}