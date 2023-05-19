package com.B1team.b01.Controller;

import com.B1team.b01.Service.StockService;
import com.B1team.b01.entity.Order;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
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

    private final StockService stockService;

    @GetMapping("")
    public String getCompare(@PathVariable String productId, String orderId, Model model) {
        //재고량 & 주문량 비교
        stockService.stockCheck(productId,orderId);

//
//        model.addAllAttributes()
        return "index";

    }
}