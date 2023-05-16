package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="WORDER")
@Getter
@Setter
@NoArgsConstructor
public class Worder {
    @Id
    @Column(name = "worder_id", nullable = false, columnDefinition = "number(10)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worder_seq_generator")
    @SequenceGenerator(name = "worder_seq_generator", sequenceName = "WORDER_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;        //작업지시 고유번호

    @Column(name = "process_id", nullable = false, columnDefinition = "number(10)")
    private Long processId;    //공정 고유번호

    @Column(name = "wplan_id", nullable = false, columnDefinition = "number(10)")
    private Long wplanId;    //작업계획 고유번호

    @Column(name = "facility_id", nullable = false, columnDefinition = "number(10)")
    private Long facilityId;    //설비정보 고유번호

    @Column(name = "worder_finish_date", nullable = false, columnDefinition = "date")
    private LocalDateTime finishDate;   //작업 완료 일자

}