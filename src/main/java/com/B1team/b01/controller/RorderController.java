
package com.B1team.b01.controller;

import com.B1team.b01.dto.CustomerDto;
import com.B1team.b01.dto.ProductDto;
import com.B1team.b01.dto.RorderDto;
import com.B1team.b01.dto.RorderFormDto;
import com.B1team.b01.repository.CustomerRepository;
import com.B1team.b01.repository.ProductRepository;
import com.B1team.b01.service.RorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/rorder")
public class RorderController {
    private final RorderService rorderService;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    //수주 리스트 검색
    @GetMapping("/order")
    public String order(Model model,
                        String startDate,
                        String endDate,
                        String orderId,
                        String status,
                        String customerName,
                        String productName,
                        String startDeadline,
                        String endDeadline) {

        //거래처 리스트
        List<CustomerDto> customerDtoList = CustomerDto.of(customerRepository.findAll());
        model.addAttribute("customerList", customerDtoList);

        //품목 리스트
        List<ProductDto> productDtoList = ProductDto.of(productRepository.findAll());
        model.addAttribute("productList", productDtoList);

        //기본 수주 조회 리스트
        List<RorderDto> rorderList = rorderService.searchRorder(startDate, endDate, orderId, status, customerName, productName, startDeadline, endDeadline);

        model.addAttribute("rorderList", rorderList);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("orderId", orderId);
        model.addAttribute("status", status);
        model.addAttribute("customerName", customerName);
        model.addAttribute("productName", productName);
        model.addAttribute("startDeadline", startDeadline);
        model.addAttribute("endDeadline", endDeadline);
//        return "rorder/order-content";
        return "rorder/order";
    }


    //수주 등록 시 - 시뮬레이션(예측)
    @PostMapping("/simulation")
    public ResponseEntity<?> orderDeliveryDate(String orderDate, String productId, String orderCnt) {
        Map<String, String> response = new HashMap<>();
        System.out.println("orderDateStr=" + orderDate);
        String deliveryDate = rorderService.calculateOrderDeliveryDate(orderDate, productId, orderCnt);
        response.put("deliveryDate", deliveryDate);
        // JSON 형태의 응답과 함께 상태 코드 200을 반환
        return ResponseEntity.ok(response);
    }

    //수주 등록
    @PostMapping("/reg")
    public ResponseEntity<?> regOrder(String orderDate,
                           String customerId,
                           String customerName,
                           String productId,
                           String productName,
                           String orderCnt,
                           String deliveryDate) {
        RorderFormDto dto = new RorderFormDto(orderDate, customerId, customerName, productId, productName, orderCnt, deliveryDate);
//        System.out.println("입력 정보=" + dto);
        rorderService.addRorder(dto);

        //View에 보내줄 내용
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/rorder/order");
        return ResponseEntity.ok(response);
    }
}
