
package com.B1team.b01.controller;

import com.B1team.b01.dto.CustomerDto;
import com.B1team.b01.dto.RorderDto;
import com.B1team.b01.repository.CustomerRepository;
import com.B1team.b01.service.RorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/rorder")
public class RorderController {
    private final RorderService rorderService;
    private final CustomerRepository customerRepository;

    @GetMapping("/order")
    public String order(Model model,
                        String startDate,
                        String endDate,
                        String orderId,
                        String customerName,
                        String productName,
                        String startDeadline,
                        String endDeadline) {

        //거래처 리스트
        List<CustomerDto> customerDtoList = CustomerDto.of(customerRepository.findAll());
        model.addAttribute("customerList", customerDtoList);

        //기본 수주 조회 리스트
        List<RorderDto> rorderList = rorderService.searchRorder(startDate, endDate, orderId, customerName, productName, startDeadline, endDeadline);

        model.addAttribute("rorderList", rorderList);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("orderId", orderId);
        model.addAttribute("customerName", customerName);
        model.addAttribute("productName", productName);
        model.addAttribute("startDeadline", startDeadline);
        model.addAttribute("endDeadline", endDeadline);
//        return "rorder/order-content";
        return "rorder/order";
    }
}
