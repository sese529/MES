package com.B1team.b01;

import com.B1team.b01.service.WorderService;
import com.B1team.b01.dto.WorderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class WorderServiceTests {
    @Autowired private WorderService worderService;



    //작업중인지 설비 확인하기
    /*@Test
    public void testcheckWorder(){
        String list = worderService.checkWorder("A102");

        if(!list.isEmpty()){
            System.out.println(list);
        }else{
            System.out.println("list 없음");
        }
    }
    */


    //작업지시 등록하기
    @Test
    public void testdoWorder(){

        LocalDateTime materialReadyDate = LocalDateTime.of(2023, 4, 26, 10, 0);
        worderService.doWorder("ROD38",materialReadyDate,"p24");
    }
}
