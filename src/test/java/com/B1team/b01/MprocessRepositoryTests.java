package com.B1team.b01;

import com.B1team.b01.entity.Mprocess;
import com.B1team.b01.entity.Routing;
import com.B1team.b01.repository.MprocessRepository;
import com.B1team.b01.repository.RoutingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        //라우팅에서 석류젤리 공정 흐름 얻기
        List<Routing> routings = routingRepository.findByProductIdOrderByOrder("p23");
        List<String> list = new ArrayList<>();
        for(int i = 0; i < routings.size(); i++) {
            list.add(routings.get(i).getProcessId());
            msg += "공정" + (i + 1) + " = " + list.get(i) + "\n";
        }

        msg += "---------------------------------------\n";

        //라우팅 정보 기반으로 공정 정보 받기
        List<Mprocess> mprocesses = mprocessRepository.findAllById(list);
        for(int i = 0; i < mprocesses.size(); i++)
            msg += mprocesses.get(i).toString() + "\n";

        System.out.println(msg);
    }
}
