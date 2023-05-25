package com.B1team.b01.controller;

import com.B1team.b01.dto.ProductDto;
import com.B1team.b01.dto.StockDto;
import com.B1team.b01.dto.StockListDto;
import com.B1team.b01.entity.Product;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.service.BomService;
import com.B1team.b01.service.ProductService;
import com.B1team.b01.service.ProductionService;
import com.B1team.b01.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/item/*")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;
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

        model.addAttribute("stockList", stockList);

        return "/item/stock";
    }
//
//    @PostMapping("/update")
//    public String stockUpdate(StockListDto sdto) {
//
//        return "/item/stock";
//    }

    @Transactional
    public String generateId(String head, String seqName) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT " + seqName + ".NEXTVAL FROM dual").getSingleResult();
        String id = head + sequenceValue;
        return id;
    }

    @PostMapping("/register")
    public String insertProduct(Product product) {

        product.setId(generateId("P", "product_seq"));
        productService.insertProduct(product);
//        product.setId(product.getId());
//        product.setName(product.getName());
//        product.setPrice(product.getPrice());
//        product.setSort(product.getSort());
//        product.setLocation(product.getLocation());

        return "/item/stock";
    }
//
    //삭제
//    @PostMapping("/delete")
//    public String stockDelete(StockListDto sdto) {
//        stockService.
//
//        return "/item/stock";
//    }
}
