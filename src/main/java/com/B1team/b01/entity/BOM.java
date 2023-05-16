package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="BOM")
@Getter
@Setter
@NoArgsConstructor
public class BOM {
    @Id
    @Column(name = "bom_id", nullable = false, columnDefinition = "number(10)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bom_seq_generator")
    @SequenceGenerator(name = "bom_seq_generator", sequenceName = "bom_seq", initialValue = 1, allocationSize = 1)
    //bom 고유번호
    private Long id;

    //원자재 고유번호
    @Column(name="mtr_id", nullable = false, columnDefinition = "number(10)")
    private Long mtrId;

    //제품 고유번호
    @Column(name="product_id", nullable = false, columnDefinition = "number(10)")
    private Long productId;

    //자재명
    @Column(name="mtr_name", nullable = false, columnDefinition = "number(10)")
    private Long mtrName;

    //용량
    @Column(name="bom_volume", nullable = false, columnDefinition = "number(10)")
    private Long volume;

}
