package com.B1team.b01.controller;

import com.B1team.b01.dto.BomListDto;
import com.B1team.b01.entity.BOM;
import com.B1team.b01.entity.Product;
import com.B1team.b01.service.BomService;
import com.B1team.b01.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/item/*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BomService bomService;

    @GetMapping("/product")
    public String productList(Model model
                                , @RequestParam(required = false) String productName
                                , @RequestParam(required = false) String productId
                                , @RequestParam(required = false) String productSort){


        List<Product> productList = productService.ProductList(productName, productId, productSort);
        model.addAttribute("productList",productList);

        return "/item/product";
    }
//
//    @GetMapping("/bom")
//    public String BOM(Model model
//            , @RequestParam(required = false) String productName
//            , @RequestParam(required = false) String mtrName) {
//
//        List<BomListDto> bomList = bomService.BomList(productName, mtrName);
//        List<BomListDto> NameList = bomService.BomName();
//
//        model.addAttribute("bomList",bomList);
//        model.addAttribute("NameList",NameList);
//
//
//        return "/item/bom";
//    }
}