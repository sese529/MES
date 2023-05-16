package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name="mprocess")
@Getter
@Setter
@NoArgsConstructor
public class Process {
    @Id
    @Column(name = "process_id", nullable = false, columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "process_seq_generator")
    @SequenceGenerator(name = "process_seq_generator", sequenceName = "PROCESS_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name="process_name", nullable = false, columnDefinition = "varchar2(20)")
    private String name;

    @Column(name="process_min_cnt", nullable = false, columnDefinition = "number")
    private Long min;

    @Column(name="process_max_cnt", nullable = false, columnDefinition = "number")
    private Long max;

    @Column(name="process_leadtime", nullable = false, columnDefinition = "number")
    private Long leadtime;

    @Column(name="process_prod_time", nullable = false, columnDefinition = "number")
    private Long prodtime;
}
