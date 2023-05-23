package com.B1team.b01.controller;

import com.B1team.b01.dto.StockDto;
import com.B1team.b01.entity.Product;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.service.BomService;
import com.B1team.b01.service.ProductionService;
import com.B1team.b01.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/item/*")
public class StockController {

    @Autowired
    private BomService bomService;

    @Autowired
    private StockService stockService;
    @Autowired
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @GetMapping("product")
    public String mappTest(Model model
                            , StockDto stockDto) {

//        List<Stock> stockList = stockService.getProductStock();
        List<Product> stList = stockService.getProductStock1(stockDto.getProductId());

//        stockList.add((Stock) stList);

        model.addAttribute("stockList",stList);


//        model.addAttribute("st",stList);

        return "/item/stock";
    }
}