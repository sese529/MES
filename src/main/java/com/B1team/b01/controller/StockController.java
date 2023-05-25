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
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/item/*")
public class StockController {

    @Autowired
    private StockService stockService;
//
//    @GetMapping("/list")
//    public String search(Model model
//            , @RequestParam(required = false) String productName
//            , @RequestParam(required = false) String productId
//            , @RequestParam(required = false) String productSort){
//        List<StockListDto> stockList;
//
//        if (productName == null && productId == null && productSort == null) {
//            // productName, productId, productSort가 모두 null인 경우 전체 리스트를 가져옴
//            stockList = stockService.getAllStockList();
//        } else {
//            // productName, productId, productSort 중 하나 이상이 값으로 전달된 경우 검색 조건에 따라 리스트를 가져옴
//            stockList = stockService.getProductStockList(productName, productId, productSort);
//        }
//
//        model.addAttribute("stockList", stockList);
//
//        return "/item/stock";
//    }

    @GetMapping("/stock")
    public String stock(Model model
                        , @RequestParam(required = false) String productName
                        , @RequestParam(required = false) String productId
                        , @RequestParam(required = false) String productSort) {
        List<StockListDto> stockList = stockService.getProductStockList(productName, productId, productSort);

//        if ("".equals(productName)) {
//            productName = null;
//        }
//        if ("".equals(productId)) {
//            productId = null;
//        }
//        if ("".equals(productSort)) {
//            productSort = null;
//        }

        model.addAttribute("stockList", stockList);

        return "/item/stock";
    }
//
//    @PostMapping("/update")
//    public String stockUpdate(StockListDto sdto) {
//
//        return "/item/stock";
//    }
//    @PostMapping("/register")
//    public String stockRegister(StockListDto sdto) {
//        return "/item/stock";
//    }
//
    //삭제
//    @PostMapping("/delete")
//    public String stockDelete(StockListDto sdto) {
//        stockService.
//
//        return "/item/stock";
//    }


    @GetMapping("/item/bom")
    public String BOM() {
        return "/item/bom";
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