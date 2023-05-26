package com.B1team.b01;

import com.B1team.b01.dto.ShipmentDto;
import com.B1team.b01.repository.FinprodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FinprodRepositoryTests {
    @Autowired FinprodRepository finprodRepository;

    @Test
    void 테스트() {
        List<ShipmentDto> list = finprodRepository.findShipmentsByConditions(null, null, "ROD38", null, null);
        for(ShipmentDto dto : list)
            System.out.println(dto);
    }
}
