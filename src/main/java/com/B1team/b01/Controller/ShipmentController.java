package com.B1team.b01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;

@Controller
@Transactional
@RequiredArgsConstructor
public class ShipmentController {
    @GetMapping("/shipment/shipment")
    public String shipment() {
        return "shipment/shipment";
    }
}
