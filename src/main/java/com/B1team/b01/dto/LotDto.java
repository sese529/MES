package com.B1team.b01.dto;

import com.B1team.b01.entity.LOT;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LotDto {

    private String id;   //작업계획 고유번호
    private String orderId;    //수주 고유번호
    private String code;    //로트 코드
    private String process; //공정 코드
    private String product; //제품명
    private String worderId;  //작업지시 고유번호
    private LocalDateTime date;  //로트 완료일
    private String porderId;  //발주 고유번호
    private LocalDateTime arrivalDate;  //원자재 입고일

    private static ModelMapper modelMapper = new ModelMapper(); //엔티티랑 dto의 필드명이 같은 것끼리 매핑

    //LOT 엔티티를 LotDto로 변환해주는 메소드
    public static LotDto of(LOT lot) {
        return modelMapper.map(lot, LotDto.class);
    }

    //LotDto를 LOT 엔티티로 변환해주는 메소드
    public LOT toEntity() {
        return modelMapper.map(this, LOT.class);
    }

}
