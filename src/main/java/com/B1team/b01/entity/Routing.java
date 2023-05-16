package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ROUTING")
@Getter
@Setter
@NoArgsConstructor
public class Routing {
    @Id
    @Column(name = "routing_id", nullable = false, columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routing_seq_generator")
    @SequenceGenerator(name = "routing_seq_generator", sequenceName = "ROUTING_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;        //원자재 고유번호
}
