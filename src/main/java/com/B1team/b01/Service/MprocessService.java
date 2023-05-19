package com.B1team.b01.Service;

import com.B1team.b01.dto.WorderDto;
import com.B1team.b01.entity.Facility;
import com.B1team.b01.entity.Mprocess;
import com.B1team.b01.entity.Routing;
import com.B1team.b01.repository.FacilityRepository;
import com.B1team.b01.repository.MprocessRepository;
import com.B1team.b01.repository.RoutingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MprocessService {
    private final MprocessRepository mprocessRepository;
    private final RoutingRepository routingRepository;

    private final FacilityRepository facilityRepository;

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

        //설비명만 list로 바꾸기 + 같은 공정의 설비 개수 세기
        List<String> facilityNameList = new ArrayList<>();  //설비명 리스트
        for(int i = 0; i < mprocesses.size(); i++) {
            facilityNameList.add(mprocesses.get(i).getFacilityId());
        }

        //공정에 따른 설비 리스트 받기
        List<Facility> facilities = facilityRepository.findByNameIn(facilityNameList);

        //같은 공정에서 설비 개수 세기
        int[] facilityCnt = new int[mprocesses.size()]; //같은 공정의 설비 개수
        int processCnt = 0;
        String tmpStr = "";
        for(int i = 0 ; i < facilities.size(); i++) {
            if(tmpStr.equals(facilities.get(i).getName()))
                facilityCnt[processCnt]++;
            else {
                facilityCnt[++processCnt]++;
                tmpStr = facilities.get(i).getName();
            }
        }

        //공정별 처리
        for(int i = 0; i < mprocesses.size(); i++) {
            Mprocess process = mprocesses.get(i);   //이번 공정 정보

            //임시로 필요 용량 넣기
            double capacity = 0;
            switch(process.getName()) {
                case "전처리": capacity = 1000 / facilityCnt[i]; break;  //양배추 1000kg / 설비 개수
                case "추출": case "혼합+살균": capacity = 1600 / facilityCnt[i]; break; //양배추 추출액 1600kg / 설비 개수
                case "충진(파우치)": case "검사": capacity = 20010 / facilityCnt[i];   //양배추즙 파우치 20010개 / 설비 개수
                case "포장" : capacity = 667 / facilityCnt[i];   //수주받은 양배추즙 667Box / 설비 개수
            }

            //이전 작업 완료 시간을 현재 공정의 작업 시작 시작으로 설정 (점심시간 & 퇴근시간 고려)
            startDate = calculateAdjustedStartTime(finishDate);

            //작업 완료 시간 세팅
            finishDate = calculateProcessFinishTime(startDate, process, capacity);

            //작업 지시 dto 추가
            WorderDto dto = new WorderDto(process.getId(), process.getFacilityId(), startDate, finishDate);        //이번 공정의 작업지시dto
            dtoList.add(dto);
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

    //시뮬레이션 - 작업 시간 계산 - 점심시간 & 퇴근시간 고려
    public LocalDateTime calculateAdjustedStartTime(LocalDateTime date) {
        if (date.getHour() >= 12 && date.getHour() < 13)    //점심시간
            return date.withHour(13).withMinute(0).withSecond(0).withNano(0);
        else if (date.getHour() < 9)    //출근 시간 이전
            return date.withHour(9).withMinute(0).withSecond(0).withNano(0);
        else if (date.getHour() >= 18)  //퇴근 시간 이후
            return date.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
        else return date;
    }
}
