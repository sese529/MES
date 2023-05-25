package com.B1team.b01;

import com.B1team.b01.entity.Worder;
import com.B1team.b01.repository.WorderRepository;
import com.B1team.b01.service.FinfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class FinfoServiceTests {
    @Autowired FinfoService finfoService;
    @Autowired WorderRepository worderRepository;

    @Test
    void 설비명출력테스트() {
        List<String> list = finfoService.getFacilityNameList();
        System.out.println(list);
    }

    @Test
    void 현재가동중인설비찾기() {
        List<Worder> list = worderRepository.findWordersByFacilityIdAndCurrentDateTime("fid", LocalDateTime.now());
        System.out.println("----------------list");
        System.out.println(list.isEmpty() ? "미가동" : "가동중");
    }
}
