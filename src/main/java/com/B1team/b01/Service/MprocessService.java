package com.B1team.b01.Service;

import com.B1team.b01.dto.WorderDto;
import com.B1team.b01.entity.Mprocess;
import com.B1team.b01.entity.Routing;
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
        List<String> producctIdlist = new ArrayList<>();
        for(int i = 0; i < routings.size(); i++)
            producctIdlist.add(routings.get(i).getProcessId());

        //라우팅에 따른 공정 리스트 받기
        List<Mprocess> mprocesses = mprocessRepository.findAllById(producctIdlist);

        int cnt = 1; //order 판정 기준

        //같은 공정에서 설비 개수 세기
        long lastOrder = routings.get(routings.size() - 1).getOrder(); //마지막 공정의 순서번호(양배추즙 포장은 8)
        int[] orderCnt = new int[(int)lastOrder]; //순서번호의 개수
        for(int i = 0 ; i < routings.size(); i++) {
            long currentOrderCnt = routings.get(i).getOrder() - 1;
            orderCnt[(int)currentOrderCnt]++;
        }

        //공정별 처리
        for(int i = 0; i < mprocesses.size(); i++) {
            WorderDto dto = new WorderDto();        //이번 공정의 작업지시dto
            Mprocess process = mprocesses.get(i);   //이번 공정 정보

            //currentFacilityCnt : 같은 공정의 설비 개수(예: 혼합+살균 탱크 2개)
            long currentOrderCnt = routings.get(i).getOrder() - 1;
            int currentFacilityCnt = orderCnt[(int)currentOrderCnt];

            //임시로 필요 용량 넣기
            double capacity = 0;
            switch(process.getName()) {
                case "전처리": capacity = 1000 / currentFacilityCnt; break;  //양배추 1000kg / 설비 개수
                case "추출": case "혼합+살균": capacity = 1600 / currentFacilityCnt; break; //양배추 추출액 1600kg / 설비 개수
                case "충진(파우치)": case "검사": capacity = 20010 / currentFacilityCnt;   //양배추즙 파우치 20010개 / 설비 개수
                case "포장" : capacity = 667 / currentFacilityCnt;   //수주받은 양배추즙 667Box / 설비 개수
            }

            //같은 공정 이미 계산했는지 확인
            if(routings.get(i).getOrder() != cnt++) {
                cnt--;
            } else {
                //이전 작업 완료 시간을 현재 공정의 작업 시작 시작으로 설정
                startDate = finishDate;

                //작업 시작 시간 - 점심시간 & 퇴근 시간 고려
                if (startDate.getHour() >= 12 && startDate.getHour() < 13)
                    startDate = startDate.withHour(13).withMinute(0).withSecond(0).withNano(0);
                else if (startDate.getHour() < 9)
                    startDate = startDate.withHour(9).withMinute(0).withSecond(0).withNano(0);
                else if (startDate.getHour() >= 18)
                    startDate = startDate.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);

                //작업 완료 시간 세팅
                finishDate = startDate;

                if (process.getName().equals("원료계량")) {
                    finishDate = finishDate.plusMinutes(process.getLeadtime() + process.getProdtime());
                } else if (process.getName().equals("혼합+살균")) {
                    double min = (process.getLeadtime() + process.getProdtime()) * (capacity / process.getCapa() + (capacity % process.getCapa() > 0 ? 1 : 0));
                    long nanos = (long) (min * 60 * 1000000000);
                    finishDate = finishDate.plusNanos(nanos);
                } else if (process.getName().equals("식힘")) {
                    finishDate = finishDate.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
                } else {
                    double min = process.getLeadtime() + process.getTimeUnit() * capacity / process.getCapa();
                    long nanos = (long) (min * 60 * 1000000000);
                    finishDate = finishDate.plusNanos(nanos);
                }
            }

            dto.setProcessId(process.getId());  //공정 고유번호
            dto.setFacilityId(process.getFacilityId()); //설비정보 고유번호
            dto.setStartDate(startDate);        //작업 시작 일자
            dto.setFinishDate(finishDate);      //작업 완료 일자

            dtoList.add(dto);
        }

        return dtoList;
    }
}
