
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
@RequestMapping("/rorder/order")
public class RorderController {
    private final RorderService rorderService;
    private final CustomerRepository customerRepository;

    @GetMapping("")
    public String order(Model model,
                        String startDate,
                        String endDate,
                        String orderId,
                        String customerName,
                        String productName,
                        String startDeadline,
                        String endDeadline) {
        //날짜 관련 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime start = startDate == null || "".equals(startDate)? null : LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime end = endDate == null || "".equals(endDate) ? null : LocalDate.parse(endDate, formatter).atTime(23, 59, 59);
        LocalDateTime startLine = startDeadline == null || "".equals(startDeadline) ? null : LocalDate.parse(startDeadline, formatter).atStartOfDay();
        LocalDateTime endLine = endDeadline == null || "".equals(endDeadline) ? null : LocalDate.parse(endDeadline, formatter).atTime(23, 59, 59);

        //거래처 리스트
        List<CustomerDto> customerDtoList = CustomerDto.of(customerRepository.findAll());
        model.addAttribute("customerDtoList", customerDtoList);

        //기본 수주 조회 리스트
        List<RorderDto> list = rorderService.searchRorder(start, end, orderId, customerName, productName, startLine, endLine);

        model.addAttribute("rorderList", list);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("orderId", orderId);
        model.addAttribute("customerName", customerName);
        model.addAttribute("productName", productName);
        model.addAttribute("startDeadline", startDeadline);
        model.addAttribute("endDeadline", endDeadline);
        return "rorder/order-content";
//        return "rorder/order";
    }
}
