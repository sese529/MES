package com.B1team.b01.service;

import com.B1team.b01.dto.WorderDto;
import com.B1team.b01.entity.Finfo;
import com.B1team.b01.entity.Mprocess;
import com.B1team.b01.entity.Routing;
import com.B1team.b01.repository.FinfoRepository;
import com.B1team.b01.repository.MprocessRepository;
import com.B1team.b01.repository.RoutingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
//@Service
@Component
public class MprocessService {
    private final MprocessRepository mprocessRepository;
    private final RoutingRepository routingRepository;
    private final FinfoRepository finfoRepository;

    //시뮬레이션 - 작업 시간 계산
    public List<WorderDto> calculateWorderDate(LocalDateTime materialReadyDate, String productId){
        //매개변수 materialReadyDate : 모든 원자재가 준비 완료되는 시간 / productId : 제품 고유번호
        List<WorderDto> dtoList = new ArrayList<>();   //반환할 작업지시(Worder) dto List

        //기준 시간 - 1. 원료계량의 시작 시간은 자재 준비 일자
        LocalDateTime startDate = materialReadyDate;     //기준이 될 시간
        LocalDateTime finishDate = startDate;

        //라우팅에서 공정 흐름 얻기
        List<Routing> routings = routingRepository.findByProductIdOrderByOrder(productId);

        //공정Id만 list로
        List<String> producctIdlist = new ArrayList<>();    //공정Id 리스트
        for(int i = 0; i < routings.size(); i++) {
            producctIdlist.add(routings.get(i).getProcessId());
        }

        //라우팅에 따른 공정 리스트 받기
        List<Mprocess> mprocesses = mprocessRepository.findAllById(producctIdlist);

        //공정별 처리
        for(int i = 0; i < mprocesses.size(); i++) {
            Mprocess process = mprocesses.get(i);   //이번 공정 정보
            List<Finfo> facilities = finfoRepository.findByName(process.getFacilityId()); //공정의 설비 정보

            //임시로 필요 용량 넣기
            double capacity = 0;
            switch(process.getName()) {
                case "전처리": capacity = 1000 / facilities.size(); break;  //양배추 1000kg / 설비 개수
                case "추출": case "혼합+살균": capacity = 1600 / facilities.size(); break; //양배추 추출액 1600kg / 설비 개수
                case "충진(파우치)": case "검사": capacity = 20010 / facilities.size();   //양배추즙 파우치 20010개 / 설비 개수
                case "포장" : capacity = 667 / facilities.size();   //수주받은 양배추즙 667Box / 설비 개수
            }

            //이전 작업 완료 시간을 현재 공정의 작업 시작 시작으로 설정 (점심시간 & 퇴근시간 고려)
            startDate = calculateAdjustedStartTime(finishDate);

            //작업 완료 시간 세팅
            finishDate = calculateProcessFinishTime(startDate, process, capacity);

            //주말 판정
            for(LocalDateTime start = startDate; start.isBefore(finishDate) || start.isEqual(finishDate); start = start.plusDays(1)) {
                if (start.getDayOfWeek() == DayOfWeek.SATURDAY || start.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    finishDate.plusDays(1);
                }
            }

            //작업 지시 dto 추가
            for(int j = 0; j < facilities.size(); j++) {
                WorderDto dto = new WorderDto(process.getId(), facilities.get(j).getId(), startDate, finishDate);        //이번 공정의 작업지시dto
                dtoList.add(dto);
            }
        }

        return dtoList;
    }

    //시뮬레이션 - 작업 시간 계산 - 공정별 작업 완료 시간 계산
    public LocalDateTime calculateProcessFinishTime(LocalDateTime date, Mprocess process, double capacity) {
        if (process.getName().equals("원료계량")) {
            return date.plusMinutes(process.getLeadtime() + process.getProdtime());
        } else if (process.getName().equals("혼합+살균")) {
            double min = (process.getLeadtime() + process.getProdtime()) * (capacity / process.getCapa() + (capacity % process.getCapa() > 0 ? 1 : 0));
            long nanos = (long) (min * 60 * 1000000000);
            return date.plusNanos(nanos);
        } else if (process.getName().equals("식힘")) {
            return date.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
        } else {
            double min = process.getLeadtime() + process.getTimeUnit() * capacity / process.getCapa();
            long nanos = (long) (min * 60 * 1000000000);
            return date.plusNanos(nanos);
        }
    }

    //시뮬레이션 - 작업 시간 계산 - (작업 시작 시간) 점심시간 & 퇴근시간 고려
    public LocalDateTime calculateAdjustedStartTime(LocalDateTime date) {
        if (date.getHour() >= 12 && date.getHour() < 13)    //점심시간
            return date.withHour(13).withMinute(0).withSecond(0).withNano(0);
        else if (date.getHour() < 9)    //출근 시간 이전
            return date.withHour(9).withMinute(0).withSecond(0).withNano(0);
        else if (date.getHour() >= 18)  //퇴근 시간 이후
            return date.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
        else return date;
    }

    //시뮬레이션 - 작업 시간 계산 - (작업 완료 시간) 점심시간 & 퇴근시간 고려
    public LocalDateTime calculateAdjustedFinishTime(LocalDateTime start, LocalDateTime finish) {
        LocalDateTime finishDate = finish;

        //점심시간
//        if(start.getHour() > )
        return finishDate;
    }
}
