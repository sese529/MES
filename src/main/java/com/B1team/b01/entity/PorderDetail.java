package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PORDERDETAIL")
@Getter
@Setter
@NoArgsConstructor
public class PorderDetail {
    @Id
    @Column(name = "porder_detail_id", nullable = false, columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "process_seq_generator")
    @SequenceGenerator(name = "process_seq_generator", sequenceName = "PROCESS_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;    //발주 품목 고유번호

    @Column(name="porder_id", nullable = false, columnDefinition = "number")
    private String porderId;    //발주 고유번호

    @Column(name="mtr_id", nullable = false, columnDefinition = "number")
    private String mtrId;   //원자재 고유번호

    @Column(name="porder_detail_cnt", nullable = false, columnDefinition = "number")
    private String detailCnt;   //발주 품목량

    @Column(name="porder_detail_price", nullable = false, columnDefinition = "number")
    private String detailPrice;   //품목 금액

}
