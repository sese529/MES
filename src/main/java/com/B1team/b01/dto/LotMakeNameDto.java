package com.B1team.b01.dto;

import com.B1team.b01.entity.LOT;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LotMakeNameDto {


    private String product; //제품명
    private String process;  //공정과정
    private String  date;  //공정완료 날짜( ':' 제외)


    //private static ModelMapper modelMapper = new ModelMapper(); //엔티티랑 dto의 필드명이 같은 것끼리 매핑

    //LOT 엔티티를 LotDto로 변환해주는 메소드
   // public static LotMakeNameDto of(LOT lot) {
   //     return modelMapper.map(lot, LotMakeNameDto.class);
   // }

    //LotDto를 LOT 엔티티로 변환해주는 메소드
   // public LOT toEntity() {
    //    return modelMapper.map(this, LOT.class);
    //}

}
