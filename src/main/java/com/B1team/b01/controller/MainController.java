package com.B1team.b01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/order")
    public String order() {
        return "order";
    }

    @GetMapping("/placeAnOrderStatus")
    public String placeAnOrderStatus() {
        return "placeAnOrderStatus";
    }

    @GetMapping("/materialInOutPut")
    public String materialInOutPut() {
        return "materialInOutPut";
    }

    @GetMapping("/materialInventory")
    public String materialInventory() {
        return "materialInventory";
    }

    @GetMapping("/productionOrder")
    public String productionOrder() {
        return "productionOrder";
    }

    @GetMapping("/productionPlan")
    public String productionPlan() {
        return "productionPlan";
    }

    @GetMapping("/productionPerformance")
    public String productionPerformance() {
        return "productionPerformance";
    }

    @GetMapping("/shipment")
    public String shipment() {
        return "shipment";
    }

    @GetMapping("/orderCustomer")
    public String orderCustomer() {
        return "orderCustomer";
    }

    @GetMapping("/placeAnOrderCustomer")
    public String placeAnOrderCustomer() {
        return "placeAnOrderCustomer";
    }

    @GetMapping("/stock")
    public String stock() {
        return "stock";
    }

    @GetMapping("/facilityStatus")
    public String facilityStatus() {
        return "facilityStatus";
    }

    @GetMapping("/facilityInformation")
    public String facilityInformation() {
        return "facilityInformation";
    }

}
