package com.B1team.b01.dto;

import com.B1team.b01.entity.Materials;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class MaterialsDto {

    private String id;        //원자재 고유번호
    private String name;    //자재명
    private String location;    //창고위치
    private Long price;    //구매단가
    private String unit;    //단위
    private Long minCnt;    //발주 최소 수량
    private  Long maxCnt;    //발주 최대 수량
    private Long leadtime;    //발주 리드타임(발주하고 입고까지 걸리는 시간)
    private Long cutoffTime;    //발주 주문 처리 기준 시간(~시보다 뒤에 주문하면 다음날로 주문 처리)
    private static ModelMapper modelMapper = new ModelMapper();

    //Materials 엔티티를 MaterialsDto로 변환해주는 메소드
    public static MaterialsDto of(Materials materials) {
        return modelMapper.map(materials, MaterialsDto.class);
    }

}
