package com.B1team.b01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    //수주 페이지 분리하면서 주석 처리 (삭제 가능)
//    @GetMapping("/order")
//    public String order(Model model) {
//
//        return "rorder/order";
//    }

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

    //출하 페이지 분리하면서 주석 처리 (삭제 가능)
//    @GetMapping("/shipment")
//    public String shipment() {
//        return "shipment";
//    }

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

    //설비 페이지 분리하면서 주석 처리(삭제 가능)
//    @GetMapping("/facilityStatus")
//    public String facilityStatus() {
//        return "facilityStatus";
//    }
//
//    @GetMapping("/facilityInformation")
//    public String facilityInformation() {
//        return "facilityInformation";
//    }

}
