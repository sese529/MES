package com.B1team.b01.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RorderFormDto {
    public String customerId;
    public String customerName;
    public String productId;
    public String productName;
    public Long cnt;
    public Long price;
    public String state;
    public String date;
    public String changedate;
    public String deadline;
}
