package com.B1team.b01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/materials/porder-status")
    public String placeAnOrderStatus() {
        return "/materials/porder-status";
    }

    @GetMapping("/materials/material-inoutput")
    public String materialInOutPut() {
        return "/materials/material-inoutput";
    }

    @GetMapping("/materials/material-inventory")
    public String materialInventory() {
        return "/materials/material-inventory";
    }
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
        return "/main";
    }

}
