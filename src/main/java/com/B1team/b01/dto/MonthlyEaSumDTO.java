package com.B1team.b01.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyEaSumDTO {
    private int month;
    private long sumEa;
}
