package com.B1team.b01.Service;

import com.B1team.b01.entity.Rorder;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.RorderRepository;
import com.B1team.b01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private RorderRepository rorderRepository;

    //시뮬레이션 - 재고확인 및 자동발주
    public Long stockCheck (String productId, String orderId) {
        //제품 잔여수량 확인
//        Optional<Stock> optional = stockRepository.findById(productId);
//        Stock stock = optional.get();

        Stock stock = stockRepository.findByProductId(productId);

        //재고 수량

        Long stockEa = stock.getEa();


        System.out.println("stockEa" +stockEa);

//      수주 주문 수량
        Optional<Rorder> optionalOne = rorderRepository.findById(orderId);
        Rorder order = optionalOne.get();

        //주문량
        Long orderCnt =  order.getCnt();

        System.out.println("orderCnt"+orderCnt);

        // 재고량 & 주문량 비교
        if(stockEa < orderCnt) {
            //재고량이 주문량보다 적으면 자동발주 계산
            System.out.println("발주계산으로 넘어감");
            return 0L;

        } else if (stockEa >= orderCnt) {
            System.out.println("출하");
            return stockEa;

        }else {
            return null;
        }

    }
}
