package com.B1team.b01;

import com.B1team.b01.dto.RorderDto;
import com.B1team.b01.entity.Rorder;
import com.B1team.b01.repository.RorderRepository;
import com.B1team.b01.service.RorderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class RorderRepositoryTests {
    @Autowired private RorderRepository rorderRepository;
    @Autowired private RorderService rorderService;
    @Test
    void 수주검색테스트() {
        //검색 조건
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        String orderId = null;
        String customerName = null;
        String productName = null;
        LocalDateTime startDeadline = LocalDateTime.of(2023, 05, 25, 0, 0, 0, 0);
        LocalDateTime endDeadLine = LocalDateTime.of(2023, 05, 25, 23, 59, 59, 0);

        List<Rorder> list = rorderRepository.findRordersByConditions(startDate, endDate, orderId, customerName, productName, startDeadline, endDeadLine);
        for(int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }

    @Test
    void 수주검색서비스테스트() {
        //검색 조건
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        String orderId = null;
        String customerName = null;
        String productName = null;
        LocalDateTime startDeadline = LocalDateTime.of(2023, 05, 25, 0, 0, 0, 0);
        LocalDateTime endDeadLine = LocalDateTime.of(2023, 05, 25, 23, 59, 59, 0);

        List<RorderDto> list = rorderService.searchRorder(startDate, endDate, orderId, customerName, productName, startDeadline, endDeadLine);
        for(int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }
}
