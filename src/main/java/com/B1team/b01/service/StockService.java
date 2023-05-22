package com.B1team.b01.service;

import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    //시뮬레이션 - 재고확인 및 자동발주
    public Long stockCheck (String productId, Long mtrEa, Long poderCnt) {
        //제품 잔여수량 확인
        Optional<Stock> optional = stockRepository.findById(productId);
        Stock stock = optional.get();

        //재고 수량
        Long stockMtrEa = stock.getEa();

//        //수주 주문 수량
//        poderCnt =


        return stockMtrEa;

    }
}
