package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="FINFO")
@Getter
@Setter
@NoArgsConstructor
public class Finfo {
    @Id
    @Column(name = "mtr_id", nullable = false, columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mtr_seq_generator")
    @SequenceGenerator(name = "mtr_seq_generator", sequenceName = "PROCESS_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;        //ㅛㅓㅎ지 고유번호
}
