package com.B1team.b01.controller;

import com.B1team.b01.entity.Product;
import com.B1team.b01.entity.Wplan;
import com.B1team.b01.service.ProductService;
import com.B1team.b01.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/production/*")
public class ProductionController {
    @Autowired
    private ProductionService productionService;
    @Autowired
    private ProductService productService;


    @GetMapping("production-order")
    public String getOrderList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        List<Wplan> wlist = productionService.getAllWplan();
        model.addAttribute("wlist", wlist);
        List<Product> plist = productService.getAllProduct();
        model.addAttribute("plist",plist);

        return "production/production-order";
    }

    @PostMapping("/search")
    public String wplanSearch(Model model,
                              @RequestParam(required = false) String id,
                              @RequestParam(required = false) String orderId,
                              @RequestParam(required = false) String state,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate min,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate max){
        LocalDateTime smin = null;
        LocalDateTime smax = null;
        if(min != null){
            smin= LocalDateTime.of(min, LocalTime.MIN);
        }

        if(max != null){
            smax = LocalDateTime.of(max, LocalTime.MAX);
        }

        if("".equals(orderId)){
            orderId=null;
        }

        System.out.println("----------------------------------------");
        System.out.println(id);
        System.out.println(orderId);
        System.out.println(state);
        System.out.println(min);
        System.out.println(max);

        List<Wplan> searchlist = productionService.search(id,orderId,state, smin, smax);
        model.addAttribute("searchlist",searchlist);

        List<Product> plist = productService.getAllProduct();
        model.addAttribute("plist",plist);

        return "production/production-order";
    }

    @GetMapping("production-plan")
    public String getWerderList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        List<Wplan> wlist = productionService.getAllWplan();
        model.addAttribute("wlist", wlist);
        List<Product> plist = productService.getAllProduct();
        model.addAttribute("plist",plist);

        return "production/production-plan";
    }
}
