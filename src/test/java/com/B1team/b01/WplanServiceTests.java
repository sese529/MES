package com.B1team.b01;

import com.B1team.b01.Service.WplanService;
import com.B1team.b01.dto.RorderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManagerFactory;
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
                .id("10")
                .changedate(LocalDateTime.now())
                .cnt(1L)
                .customerId("PCU13")
                .customerName("싱싱농장")
                .date(LocalDateTime.now())
                .deadline(LocalDateTime.now())
                .price(9000L)
                .productId("P21")
                .productName("양배추즙")
                .state("테스트중")
                .build();


        wplanService.createWplan(rorderDto);



    }

}
