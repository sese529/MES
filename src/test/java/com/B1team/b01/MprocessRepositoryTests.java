package com.B1team.b01;

import com.B1team.b01.entity.Mprocess;
import com.B1team.b01.entity.Routing;
import com.B1team.b01.repository.MprocessRepository;
import com.B1team.b01.repository.RoutingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MprocessRepositoryTests {
    @Autowired private MprocessRepository mprocessRepository;
    @Autowired private RoutingRepository routingRepository;

    @Test
    void 공정테스트() {
        //모든 공정을 불러오기
        List<String> list = new ArrayList<>(); //processId list
        for(int i = 0; i < 14; i++) {
            list.add("A" + (i + 15));
        }

        List<Mprocess> mprocesses = mprocessRepository.findAllById(list);

        String msg = "---------------------------------------\n";
        for(int i = 0; i < mprocesses.size(); i++) {
            msg += mprocesses.get(i).toString() + "\n";
        }
        msg += "---------------------------------------\n";
        System.out.println(msg);
    }

    @Test
    void 라우팅_공정_테스트() {
        String msg = "---------------------------------------\n";

        //라우팅에서 양배추즙 공정 흐름 얻기
        List<Routing> routings = routingRepository.findByProductIdOrderByOrder("p21");
        List<String> list = new ArrayList<>();
        for(int i = 0; i < routings.size(); i++) {
            list.add(routings.get(i).getProcessId());
            msg += routings.get(i).toString() + "\n";
        }

        msg += "---------------------------------------\n";

        //라우팅 정보 기반으로 공정 정보 받기
        List<Mprocess> mprocesses = mprocessRepository.findAllById(list);
        for(int i = 0; i < mprocesses.size(); i++)
            msg += mprocesses.get(i).toString() + "\n";

        msg += "---------------------------------------\n";

        //기준 시간 - 시작 시간은 자재 준비 일자
        LocalDateTime startDate = LocalDateTime.of(2023, 5, 24, 10, 0);
        LocalDateTime finishDate = LocalDateTime.of(2023, 5, 24, 10, 0);
        int cnt = 1;

        for(int i = 0; i < mprocesses.size(); i++) {
            Mprocess process = mprocesses.get(i);

            if(routings.get(i).getOrder() != cnt++) {
                cnt--;
                msg += process.getName() + " 시작 시간 : " + startDate + ", 종료 시간 : " + finishDate + ", order = " + routings.get(i).getOrder() + "\n";
                continue;
            } else {
                startDate = finishDate;
            }

            //임시로 필요 용량 넣기
            double capacity = 0;
            switch(process.getName()) {
                case "전처리": capacity = 1.5; break;  //양배추 1.5kg
                case "추출": case "혼합+살균": capacity = 2.4; break; //양배추 추출액 2.4kg
                case "충진(파우치)": case "검사": capacity = 30;   //양배추즙 파우치 30개
                case "포장" : capacity = 1;   //수주받은 양배추즙 1Box
            }

            //작업 시작 시간 - 점심시간 & 퇴근 시간 고려
            if(startDate.getHour() >= 12 && startDate.getHour() < 13)
                startDate.withHour(13).withMinute(0).withSecond(0).withNano(0);
            else if(startDate.getHour() < 9)
                startDate.withHour(9).withMinute(0).withSecond(0).withNano(0);
            else if(startDate.getHour() >= 18)
                startDate.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);

            String state = process.getName() + " 시작 시간 : ";
            state += startDate + ", 종료 시간 : ";

            finishDate = startDate;

            if(process.getName().equals("원료계량")) {
                finishDate = finishDate.plusMinutes(process.getLeadtime() + process.getProdtime());
            } else if(process.getName().equals("혼합+살균")) {
                double min = (process.getLeadtime() + process.getProdtime()) * (capacity / process.getCapa() + (capacity % process.getCapa() > 0 ? 1 : 0));
                long nanos = (long) (min * 60 * 1000000000);
                finishDate = finishDate.plusNanos(nanos);
            } else if(process.getName().equals("식힘")) {
                finishDate = finishDate.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
            } else {
                double min = process.getLeadtime() + 60 * capacity / process.getCapa();
                long nanos = (long) (min * 60 * 1000000000);
                finishDate = finishDate.plusNanos(nanos);
            }

            msg += state + finishDate + ", order = " + routings.get(i).getOrder() + "\n";
        }

        System.out.println(msg);
    }
}
