package com.B1team.b01.Service;

import com.B1team.b01.dto.WorderDto;
import com.B1team.b01.entity.Materials;
import com.B1team.b01.entity.Mprocess;
import com.B1team.b01.entity.Routing;
import com.B1team.b01.entity.Worder;
import com.B1team.b01.repository.MaterialsRepository;
import com.B1team.b01.repository.MprocessRepository;
import com.B1team.b01.repository.RoutingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MaterialsService {
    private final MaterialsRepository materialsRepository;
    private final MprocessRepository mprocessRepository;
    private final RoutingRepository routingRepository;

    //시뮬레이션 - 발주 입고 날짜 계산
    public LocalDateTime calculateArrivalDate(LocalDateTime orderDate, String materialId) {
        //매개변수 orderDate : 발주 주문 시간 / materialId : 원자재 고유 번호
        Optional<Materials> optional = materialsRepository.findById(materialId);
        Materials materials = optional.get();
        //TODO : optional이 비었을 시(nullException) 처리

        //실질 발주 주문 날짜 (발주 처리 기준 시간 이전에 발주 시 당일, 이후 발주 시 다음날)
        LocalDateTime date = orderDate.getHour() < materials.getCutoffTime() ? orderDate : orderDate.plusDays(1);

        //리드타임(발주 소요 시간) 더하기
        date = date.plusDays(materials.getLeadtime());

        //주말 판정
        if(date.getDayOfWeek().getValue() > 5)
            date = date.plusDays(2);

        //입고일자 화, 목이면 다음날로 미루기 (입고일자는 월, 수, 금)
        if(date.getDayOfWeek().getValue() == 2 || date.getDayOfWeek().getValue() == 4)
            date = date.plusDays(1);

        //입고 시간 오전 10시로 고정
        date = date.withHour(10).withMinute(0).withSecond(0).withNano(0);

        return date;
    }

    //시뮬레이션 - 작업 시간 계산
    public List<WorderDto> calculateWorderDate(LocalDateTime materialReadyDate, String productId){
        //매개변수 materialReadyDate : 모든 원자재가 준비 완료되는 시간 / productId : 제품 고유번호
        LocalDateTime date = materialReadyDate;     //기준이 될 시간
        List<WorderDto> list = new ArrayList<>();   //반환할 작업지시(Worder) dto List

        //라우팅에서 공정 흐름 얻기
        List<Routing> routings = routingRepository.findByProductIdOrderByOrder(productId);

        //공정Id만 list로
        List<String> producctIdlist = new ArrayList<>();
        for(int i = 0; i < routings.size(); i++)
            producctIdlist.add(routings.get(i).getProcessId());

        //라우팅에 따른 공정 리스트 받기
        List<Mprocess> mprocesses = mprocessRepository.findAllById(producctIdlist);

        //
        for(int i = 0; i < mprocesses.size(); i++) {
            WorderDto dto = new WorderDto();
            dto.setStartDate(date);
        }

        return list;
    }
}
