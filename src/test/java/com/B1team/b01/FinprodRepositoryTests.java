package com.B1team.b01;

import com.B1team.b01.dto.MonthlyEaSumDTO;
import com.B1team.b01.dto.ShipmentDto;
import com.B1team.b01.repository.FinprodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class FinprodRepositoryTests {
    @Autowired FinprodRepository finprodRepository;

    @Test
    void 테스트() {
        List<ShipmentDto> list = finprodRepository.findShipmentsByConditions(null, null, "ROD38", null, null, LocalDateTime.now());
        for(ShipmentDto dto : list)
            System.out.println(dto);
    }

    @Test
    void 월별생산량테스트() {
        List<MonthlyEaSumDTO> list = finprodRepository.getMonthlyEaSum();
        for(MonthlyEaSumDTO dto : list)
            System.out.println(dto);
    }
}
