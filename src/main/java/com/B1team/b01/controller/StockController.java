package com.B1team.b01.controller;

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

import javax.transaction.Transactional;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/item/*")
public class StockController {

    @Autowired
    private BomService bomService;

    @Autowired
    private StockService stockService;

    @GetMapping("product")
    public String mappTest() {
        System.out.println("d");

        return "/item/stock";
    }
}