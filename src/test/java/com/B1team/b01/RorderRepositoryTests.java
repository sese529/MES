package com.B1team.b01;

import com.B1team.b01.entity.Rorder;
import com.B1team.b01.repository.RorderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RorderRepositoryTests {
    @Autowired private RorderRepository rorderRepository;
    @Test
    void 수주검색테스트() {
        List<Rorder> list = rorderRepository.findRordersByConditions(null, null, "10", null);
        for(int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }
}
