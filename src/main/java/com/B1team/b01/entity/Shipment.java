package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="SHIPMENT")
@Getter
@Setter
@NoArgsConstructor
public class Shipment {
    @Id
    @Column(name = "shipment_id", nullable = false, columnDefinition = "number(10)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "porder_seq_generator")
    @SequenceGenerator(name = "porder_seq_generator", sequenceName = "porder_seq", initialValue = 1, allocationSize = 1)
    //출하 고유번호
    private Long id;

    //완제품 고유번호
    @Column(name="worder_id", nullable = false, columnDefinition = "number(10)")
    private Long worderId;

    //제품 고유번호
    @Column(name="product_id", nullable = false, columnDefinition = "number(10)")
    private Long customerId;

    //수량
    @Column(name="shipment_cnt", nullable = false, columnDefinition = "number(10)")
    private Long cnt;

    //품목명
    @Column(name="shipment_product", nullable = false, columnDefinition = "varchar2(20)")
    private String name;

    //출하일
    @Column(name="shipment_day", nullable = false, columnDefinition = "number(10)")
    private Long day;

    }
