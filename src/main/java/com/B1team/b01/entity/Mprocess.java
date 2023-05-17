package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name="MPROCESS")
@Getter
@Setter
@NoArgsConstructor
public class Mprocess {
    @Id
    @Column(name = "process_id", nullable = false, columnDefinition = "number(10)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "process_seq_generator")
    @SequenceGenerator(name = "process_seq_generator", sequenceName = "PROCESS_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;    //공정 고유번호

    @Column(name="process_name", nullable = false, columnDefinition = "varchar2(50)")
    private String name;    //공정명

    @Column(name="process_min_cnt", nullable = false, columnDefinition = "number(10)")
    private Long minCnt;    //최소 생산량

    @Column(name="process_max_cnt", nullable = false, columnDefinition = "number(10)")
    private Long maxCnt;    //최대 생산량

    @Column(name="facility_id", nullable = false, columnDefinition = "number(10)")
    private Long facilityId;    //설비 고유번호

    @Column(name="process_leadtime", nullable = false, columnDefinition = "number(10)")
    private Long leadtime;  //리드타임

    @Column(name="process_prod_time", nullable = false, columnDefinition = "number(10)")
    private Long prodtime;  //작업시간
}
