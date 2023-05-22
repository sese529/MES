package com.B1team.b01;

import com.B1team.b01.service.WorderService;
import com.B1team.b01.dto.WorderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
public class WorderServiceTests {
    @Autowired private WorderService worderService;




    //작업계획 수주번호 조회하기
    @Test
    public void testSeeWorder(){
        String niceto = worderService.seeWorder("10");
        System.out.println("결과값 확인" + niceto);
    }


    //작업지시하기
    @Test
    public void testdoWorder(){

        WorderDto worderDto = WorderDto.builder()
                .processId("A1")
                .wplanId("WPLAN")
                .facilityId("A101")
                .startDate(LocalDateTime.parse("2023-05-18T12:18:00"))
                .finishDate(LocalDateTime.parse("2023-05-25T09:20:00"))
                .build();


        worderService.doWorder("10",worderDto);
    }

}
