package com.B1team.b01.dto;


import lombok.*;

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
}
