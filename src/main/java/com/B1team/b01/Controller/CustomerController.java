package com.B1team.b01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/*")
public class CustomerController {
    @GetMapping("/customer/rorder-customer")
    public String rorderCustomer() {

        return "/customer/rorder-customer";
    }

    @GetMapping("/customer/porder-customer")
    public String porderCustomer() {

        return "/customer/porder-customer";
    }

    @PostMapping("/pordersearch")
    public String porderSearch(){

        return null;
    }

    @PostMapping("/rordersearch")
    public String rorderSearch(){

        return null;
    }
}
