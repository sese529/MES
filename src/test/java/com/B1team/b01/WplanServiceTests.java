package com.B1team.b01;

import com.B1team.b01.service.WplanService;
import com.B1team.b01.dto.RorderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
public class WplanServiceTests {
    @Autowired private WplanService wplanService;

    
    //문자열 시퀀스 확인하기
    //@Test
    @Transactional
    public void testGenerateWplanId() {
        String wplanId = wplanService.generateWplanId();
        System.out.println("Generated Wplan ID: " + wplanId);
    }



    //작업계획 등록하기
    @Test
    public void testResister(){

        RorderDto rorderDto = RorderDto.builder()
                .id("11")
                .changedate(LocalDateTime.now())
                .cnt(1L)
                .customerId("PCU13")
                .customerName("싱싱농장")
                .date(LocalDateTime.parse("2023-05-18T12:18:00"))
                .deadline(LocalDateTime.now())
                .price(9000L)
                .productId("P21")
                .productName("양배추즙")
                .state("진행중")
                .build();


        wplanService.createWplan(rorderDto);



    }

}
