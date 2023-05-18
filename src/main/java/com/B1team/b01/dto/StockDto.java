package com.B1team.b01.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private String id;
    private String productId;
    private String mtrId;
    private String location;
    private Long stockEa;
    private String stockUnit;
}
