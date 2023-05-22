package com.B1team.b01.dto;


import com.B1team.b01.entity.Worder;
import com.B1team.b01.entity.Wplan;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorderDto {
    public String id;   //작업지시 고유번호
    public String processId; //공정 고유번호
    public String wplanId; //작업계획 고유번호
    public String facilityId; //설비정보 고유번호
    public LocalDateTime startDate; //작업 시작 일자
    public LocalDateTime finishDate; //작업 완료 일자

    private static ModelMapper modelMapper = new ModelMapper(); //엔티티랑 dto의 필드명이 같은 것끼리 매핑

    //Worder 엔티티를 WorderDto로 변환해주는 메소드
    public static WorderDto of(Worder worder) {
        return modelMapper.map(worder, WorderDto.class);
    }

    //WplanDto를 Wplan 엔티티로 변환해주는 메소드
    public Worder toEntity() {
        return modelMapper.map(this, Worder.class);
    }

}
