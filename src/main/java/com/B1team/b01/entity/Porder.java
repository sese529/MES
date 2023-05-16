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
    private Long id;

    @Column(name="p_order_date", nullable = false, columnDefinition = "date")
    private LocalDateTime orderDate;

    @Column(name="customer_id", nullable = false, columnDefinition = "varchar2(50)")
    private String customerId;

    @Column(name="customer_name", nullable = false, columnDefinition = "varchar2(50)")
    private String customerName;

    @Column(name="arrval_date", nullable = false, columnDefinition = "date")
    private LocalDateTime arrvalDate;

    @Column(name="p_orer_cnt", nullable = false, columnDefinition = "nunmber(10)")
    private Long cnt;

    @Column(name="mtr_unit", nullable = false, columnDefinition = "varchar2(3)")
    private String unit;

    @Column(name="porder_amount", nullable = false, columnDefinition = "number(10)")
    private Long amount;

    @Column(name="porder_state", nullable = false, columnDefinition = "varchar2(12)")
    private String state;

    @Column(name="mtr_id", nullable = false, columnDefinition = "number(10)")
    private Long mtrId;

    @Column(name="mtr_price", nullable = false, columnDefinition = "varchar2(20)")
    private String mtrPrice;

    @Column(name="mtr_name", nullable = false, columnDefinition = "varchar2(15)")
    private String mtrName;



    }
