package com.B1team.b01.dto;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WplanDto {

    public String id;   //작업계획 고유번호
    public String productId; //제품 고유번호
    public String orderId; //수주 고유번호
    public Long cnt; //계획수량
    public LocalDateTime startDate; //계획 시작 일자
    public LocalDateTime endDate; //계획 완료 일자
    public String state; //작업 완료 일자

}
