package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jdbc.repository.query.Query;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="PORDER")
@Getter
@Setter
@NoArgsConstructor
public class Porder {
    @Id
    @Column(name = "p_order_id", nullable = false, columnDefinition = "number(10)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p_order_seq_generator")
    @SequenceGenerator(name = "p_order_seq_generator", sequenceName = "p_order_seq", initialValue = 1, allocationSize = 1)
    //발주 고유번호
    private Long id;

    //발주일
    @Column(name="p_order_date", nullable = false, columnDefinition = "date")
    private LocalDateTime orderDate;

    //거래처 고유번호
    @Column(name="customer_id", nullable = false, columnDefinition = "varchar2(50)")
    private String customerId;

    //발주처명
    @Column(name="customer_name", nullable = false, columnDefinition = "varchar2(50)")
    private String customerName;

    //입고일
    @Column(name="arrval_date", nullable = false, columnDefinition = "date")
    private LocalDateTime arrvalDate;

    //품목 수량
    @Column(name="p_orer_cnt", nullable = false, columnDefinition = "nunmber(10)")
    private Long cnt;

    //품목 단위
    @Column(name="mtr_unit", nullable = false, columnDefinition = "varchar2(3)")
    private String unit;

    //발주 금액
    @Column(name="porder_amount", nullable = false, columnDefinition = "number(10)")
    private Long amount;

    //입고 여부
    @Column(name="porder_state", nullable = false, columnDefinition = "varchar2(12)")
    private String state;

    //원자재 고유번호
    @Column(name="mtr_id", nullable = false, columnDefinition = "number(10)")
    private Long mtrId;

    //구매 단가
    @Column(name="mtr_price", nullable = false, columnDefinition = "varchar2(20)")
    private String mtrPrice;

    //품목명
    @Column(name="mtr_name", nullable = false, columnDefinition = "varchar2(15)")
    private String mtrName;



    }
