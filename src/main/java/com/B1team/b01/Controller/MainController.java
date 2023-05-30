package com.B1team.b01.controller;

import com.B1team.b01.service.FinprodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
@RequiredArgsConstructor
public class MainController {

    private final FinprodService finprodService;



    
    //Materials 컨트롤러 독립 - materials 관련 주석 처리(주석 부분 제거 가능)
    //    @GetMapping("/materials/porder-status")
//    public String placeAnOrderStatus() {
//        return "/materials/porder-status";
//    }
//
//    @GetMapping("/materials/material-inoutput")
//    public String materialInOutPut() {
//        return "/materials/material-inoutput";
//    }
//
//    @GetMapping("/materials/material-inventory")
//    public String materialInventory() {
//        return "/materials/material-inventory";
//    }
//

//    @GetMapping("/customer/rorder-customer")
//    public String orderCustomer() {
//        return "/customer/rorder-customer";
//    }
//
//    @GetMapping("/customer/porder-customer")
//    public String placeAnOrderCustomer() {
//        return "/customer/porder-customer";
//    }


    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/main/monthlySum")
    public ResponseEntity<?> getMonthly() {
        Map<String, String> response = new HashMap<>();
        List<Long> list = finprodService.getMonthlyList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/main/dailySum")
    public ResponseEntity<?> getDaily() {
        Map<String, String> response = new HashMap<>();
        List<Long> list = finprodService.getDailyList();
        return ResponseEntity.ok(list);
    }



}
