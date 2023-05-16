package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="MATERIALS")
@Getter
@Setter
@NoArgsConstructor
public class Materials {
    @Id
    @Column(name = "mtr_id", nullable = false, columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mtr_seq_generator")
    @SequenceGenerator(name = "mtr_seq_generator", sequenceName = "MTR_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;        //원자재 고유번호

    @Column(name="mtr_name", nullable = false, columnDefinition = "varchar2(20)")
    private String name;    //자재명

    @Column(name="mtr_location", nullable = false, columnDefinition = "varchar2(20)")
    private String location;    //창고위치

    @Column(name="mtr_price", nullable = false, columnDefinition = "varchar2(20)")
    private String price;    //구매단가

    @Column(name="mtr_unit", nullable = false, columnDefinition = "varchar2(10)")
    private String unit;    //단위
}
