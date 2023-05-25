package com.B1team.b01;

import com.B1team.b01.service.PinoutService;
import com.B1team.b01.dto.PinoutDto;
import com.B1team.b01.entity.Pinout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@SpringBootTest
public class PinoutServiceTests {
    @Autowired private PinoutService pinoutService;


    //자재출고 등록하기 테스트
 /*   @Test
    public void testRegisterPinout(){

        String wplanId = "WPLAN12";

        PinoutDto pinoutDto = PinoutDto.builder()
                .mtrId("MTR36") // (임시) 원자재 고유번호 *
                .productCnt(30L) // 출고 수량
                .productDate(LocalDateTime.now()) // (임시) 출고날짜 = 작업지시 날짜*
                .sort("출고")
                .build(); // 출고수량

           pinoutService.createInout(pinoutDto);

    }
*/
}
