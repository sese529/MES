package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="STOCK")
@Getter
@Setter
@NoArgsConstructor
public class Stock {
    @Id
    @Column(name = "stock_id", nullable = false, columnDefinition = "number(10)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq_generator")
    @SequenceGenerator(name = "stock_seq_generator", sequenceName = "stock_seq", initialValue = 1, allocationSize = 1)
    //재고 고유번호
    private Long id;

    //제품 고유번호
    @Column(name="product_id", nullable = false, columnDefinition = "number(10)")
    private Long productId;

    //창고위치
    @Column(name="location", nullable = false, columnDefinition = "varchar2(20)")
    private String location;

    //잔여수량
    @Column(name="mtr_ea", nullable = false, columnDefinition = "number(10)")
    private Long mtrEa;

    //원자재 고유번호
    @Column(name="mtr_id", nullable = false, columnDefinition = "number(10)")
    private Long mtrId;

    //단위
    @Column(name="mtr_unit", nullable = false, columnDefinition = "varchar2(10)")
    private String mtrUnit;
}
