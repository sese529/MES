package com.B1team.b01;

import com.B1team.b01.dto.PorderOutputDto;
import com.B1team.b01.entity.Porder;
import com.B1team.b01.repository.PorderRepository;
import com.B1team.b01.service.PorderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PorderRepositoryTests {
    @Autowired PorderRepository porderRepository;
    @Autowired PorderService porderService;

    @Test
    void 발주현황검색테스트() {
        List<Porder> list = porderRepository.findPordersByConditons(null, null, null, null, null, null, null, null);
        for(Porder porder : list)
            System.out.println(porder);
    }

    @Test
    void 발주현황검색서비스테스트() {
        List<PorderOutputDto> list = porderService.getPorderList(null, null, null, null, null, null, null, null);
        for(PorderOutputDto porder : list)
            System.out.println(porder);
    }
}
