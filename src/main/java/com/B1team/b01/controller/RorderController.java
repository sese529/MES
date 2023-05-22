
package com.B1team.b01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;


@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/order")
public class RorderController {
    @GetMapping("/rorder")
    public String orderMap() {
        System.out.println("d");

        return "/rorder/order";
    }
}
