package com.B1team.b01;

import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class StockServiceTest {
    @Autowired private StockRepository stockRepository;

    @Test
    void stTest() {
//        Optional<Stock> optional = stockRepository.findById("mtr38");
//        Stock stock = optional.get();

        List<Stock> stockList = stockRepository.findByProductIdNotNull();
        for (Stock stock : stockList) {
            Long stockEa = stock.getMtrEa();
            System.out.println("개수" + stockEa);

        }



    }

}
