package com.B1team.b01.controller;

import com.B1team.b01.dto.StockDto;
import com.B1team.b01.dto.StockListDto;
import com.B1team.b01.entity.Product;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.service.BomService;
import com.B1team.b01.service.ProductionService;
import com.B1team.b01.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
    private StockService stockService;

    @GetMapping("/stock")
    public String stock(Model model){
        List<StockListDto> stockList = stockService.getProductStockList();

        model.addAttribute("stockList",stockList);

        return "/item/stock";
    }
//
//    @GetMapping("product")
//    public String mappTest(Model model) {
//
//        List<StockListDto> stockList = stockService.getProductStockList();
////        List<StockDto> stList = stockService.getProductStock1(stockDto.getProductId());
//
//        model.addAttribute("stockList",stockList);
//
//        return "/item/stock";
//    }
}