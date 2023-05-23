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
    @Test
    public void testcheckWorder(){
        worderService.checkWorder("A102", 0);
    }


    //작업지시하기
    @Test
    public void testdoWorder(){

        /*WorderDto worderDto = WorderDto.builder()
                .processId("A1")
                .wplanId("WPLAN")
                .facilityId("A101")
                .startDate(LocalDateTime.parse("2023-05-18T12:18:00"))
                .finishDate(LocalDateTime.parse("2023-05-25T09:20:00"))
                .build();

*/
        LocalDateTime materialReadyDate = LocalDateTime.of(2023, 5, 22, 10, 0);
        List<WorderDto> list =  worderService.doWorder("11",materialReadyDate,"p22");
    }
}
