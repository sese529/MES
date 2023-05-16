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
    @Column(name = "routing_id", nullable = false, columnDefinition = "number(10)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routing_seq_generator")
    @SequenceGenerator(name = "routing_seq_generator", sequenceName = "ROUTING_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;        //라우팅 고유번호

    @Column(name = "product_id", nullable = false, columnDefinition = "number(10)")
    private Long productId;    //제품 고유번호

    @Column(name="process_id", nullable = false, columnDefinition = "number(10)")
    private Long processId;    //공정 고유번호(공정코드)

    @Column(name="routing_order", nullable = false, columnDefinition = "number(10)")
    private Long order;    //순서

}
