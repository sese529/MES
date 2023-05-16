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
    @Column(name = "process_id", nullable = false, columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "process_seq_generator")
    @SequenceGenerator(name = "process_seq_generator", sequenceName = "PROCESS_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
}
