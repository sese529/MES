package com.B1team.b01.controller;

import com.B1team.b01.entity.Customer;
import com.B1team.b01.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer/*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/rorder-customer")
    public String rorderCustomer(Model model) {
        String sort = "수주처";
        model.addAttribute("clist",customerService.getPorderList(sort));

        return "/customer/rorder-customer";
    }

    @GetMapping("/customer/porder-customer")
    public String porderCustomer(Model model) {
        String sort = "발주처";
        model.addAttribute("clist",customerService.getPorderList(sort));

        return "/customer/porder-customer";
    }

    @PostMapping("/pordersearch")
    public String porderSearch(Model model,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String id,
                               @RequestParam(required = false) String businessNumber){
        if("".equals(name)){
            name=null;
        }
        if("".equals(id)){
            id=null;
        }
        if("".equals(businessNumber)){
            businessNumber=null;
        }

        System.out.println(name);
        System.out.println(id);
        System.out.println(businessNumber);

        String sort="발주처";
        List<Customer> slist = customerService.search(name,id,businessNumber,sort);
        System.out.println(slist);
        model.addAttribute("slist",slist);

        return "/customer/porder-customer";
    }

    @PostMapping("/rordersearch")
    public String rorderSearch(Model model,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String id,
                               @RequestParam(required = false) String businessNumber){
        if("".equals(name)){
            name=null;
        }
        if("".equals(id)){
            id=null;
        }
        if("".equals(businessNumber)){
            businessNumber=null;
        }

        System.out.println(name);
        System.out.println(id);
        System.out.println(businessNumber);

        String sort="수주처";
        List<Customer> slist = customerService.search(name,id,businessNumber,sort);
        System.out.println(slist);
        model.addAttribute("slist",slist);

        return "/customer/rorder-customer";
    }

    @PostMapping("/pcustomer-insert")
    public String pcustomerInsert(String name, String id, String text, String tel, String fax){

        return "/customer/porder-customer";
    }

    @PostMapping("/rcustomer-insert")
    public String rcustomerInsert(){

        return "/customer/rorder-customer";
    }
}
