package com.B1team.b01.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="WPLAN")
@Getter
@Setter
@NoArgsConstructor
public class Wplan {
    @Id
    @Column(name = "wplan_id", nullable = false, columnDefinition = "number(10)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wplan_seq_generator")
    @SequenceGenerator(name = "wplan_seq_generator", sequenceName = "wplan_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name="product_id", nullable = false, columnDefinition = "number(10)")
    private Long productId;

    @Column(name="order_id", nullable = false, columnDefinition = "number(10)")
    private Long orderId;

    @Column(name="wplan_cnt", nullable = false, columnDefinition = "number(10)")
    private Long cnt;

    @Column(name="wplan_start_date", nullable = false, columnDefinition = "date")
    private LocalDateTime startDate;

    @Column(name="wplan_end_date", nullable = false, columnDefinition = "date")
    private LocalDateTime endDate;

    @Column(name="wplan_state", nullable = false, columnDefinition = "varchar2(15)")
    private LocalDateTime state;
}
