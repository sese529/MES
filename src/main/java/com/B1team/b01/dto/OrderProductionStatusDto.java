package com.B1team.b01.dto;

import com.B1team.b01.entity.Rorder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductionStatusDto {
    private String id;
    private String productName;
    private LocalDateTime date;
    private LocalDateTime deadline;
    private double duration;

    private static ModelMapper modelMapper = new ModelMapper(); //엔티티랑 dto의 필드명이 같은 것끼리 매핑

    //엔티티 -> dto 매핑
    public static OrderProductionStatusDto of(Rorder rorder) {
        OrderProductionStatusDto dto = modelMapper.map(rorder, OrderProductionStatusDto.class);
        double duration = ChronoUnit.NANOS.between(rorder.getDate(), LocalDateTime.now()) / (double)ChronoUnit.NANOS.between(rorder.getDate(), rorder.getDeadline());
        dto.setDuration(duration);
        return dto;
    }

    //Page<Rorder>를 List<Dto>로
    public static List<OrderProductionStatusDto> fromPage(List<Rorder> rorderPage) {
        List<OrderProductionStatusDto> dtos = new ArrayList<>();
        for(Rorder rorder : rorderPage) {
            dtos.add(OrderProductionStatusDto.of(rorder));
        }
        return dtos;
    }
}
