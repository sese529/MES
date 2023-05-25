package com.B1team.b01;

import com.B1team.b01.service.FinfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FinfoServiceTests {
    @Autowired
    FinfoService finfoService;

    @Test
    void 설비명출력테스트() {
        List<String> list = finfoService.getFacilityNameList();
        System.out.println(list);
    }
}
