
package com.B1team.b01.controller;

import com.B1team.b01.dto.RorderDto;
import com.B1team.b01.service.RorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/order")
public class RorderController {
    private final RorderService rorderService;

    @GetMapping("/")
    public String order(Model model,
                        LocalDateTime startDate,
                        LocalDateTime endDate,
                        String orderId,
                        String customerName,
                        String productName,
                        LocalDateTime startDeadline,
                        LocalDateTime endDeadLine) {
        List<RorderDto> list = rorderService.searchRorder(startDate, endDate, orderId, customerName, productName, startDeadline, endDeadLine);
        model.addAttribute("rorderList", list);
        return "rorder/order";
    }
}
