package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Lot")
@Getter
@Setter
@NoArgsConstructor
public class Lot {
    @Id
    @Column(name = "lot_id", nullable = false, columnDefinition = "number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lot_seq_generator")
    @SequenceGenerator(name = "lot_seq_generator", sequenceName = "LOT_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;    //로트 고유번호

    @Column(name="lot_code", nullable = false, columnDefinition = "varchar2(20)")
    private String code;    //로트 코드번호

    @Column(name="lot_product", nullable = false, columnDefinition = "varchar2(15)")
    private String product; //제품명

    @Column(name="worder_id", nullable = false, columnDefinition = "number")
    private Long worderId;

    @Column(name="lot_date", nullable = false, columnDefinition = "varchar2(15)")
    private String date;
}
