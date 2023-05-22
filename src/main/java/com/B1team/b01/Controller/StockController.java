package com.B1team.b01.Controller;

import com.B1team.b01.Service.BomService;
import com.B1team.b01.Service.StockService;
import com.B1team.b01.entity.Order;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Optional;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/stock/*")
public class StockController {

    @Autowired
    private final StockService stockService;

    @Autowired
    private final BomService bomService;

    @GetMapping("")
    public String getCompare(@PathVariable String productId, String orderId, Model model, String pid, double amount) {
        //재고량 & 주문량 비교 - 재고량 < 주문량 -> 발주계산
        stockService.stockCheck(productId,orderId);

        //발주 계산
        bomService.calcBom(pid,amount);

        //자동 발주




//
//        model.addAllAttributes()
        return "test";

    }
}