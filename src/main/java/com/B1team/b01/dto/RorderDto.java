package com.B1team.b01.dto;

import com.B1team.b01.entity.Order;
import com.B1team.b01.entity.Wplan;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RorderDto {

    public String id;
    public LocalDateTime date;
    public String customerId;
    public String customerName;
    public LocalDateTime deadline;
    public Long price;
    public String state;
    public LocalDateTime changedate;
    public String productId;
    public Long cnt;
    public String productName;


    private static ModelMapper modelMapper = new ModelMapper(); //엔티티랑 dto의 필드명이 같은 것끼리 매핑

    //Order 엔티티를 RorderDto로 변환해주는 메소드
    public static RorderDto of(Order rorder) {
        return modelMapper.map(rorder, RorderDto.class);
    }

    //RorderDto를 Order 엔티티로 변환해주는 메소드
    public Order toEntity() {
        return modelMapper.map(this, Order.class);
    }
}
