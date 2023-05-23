package com.B1team.b01.controller;

import com.B1team.b01.service.ProductionService;
import com.B1team.b01.entity.Wplan;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/production/*")
public class ProductionController {
    private ProductionService productionService;

    @Autowired
    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping("/production/production-order")
    public String getOrderList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        List<Wplan> wlist = productionService.getAllWplan();
        model.addAttribute("wlist", wlist);

        return "production/production-order";
    }

    @GetMapping("search")
    public String wplanSearch(Model model,
                              @RequestParam(required = false) String id,
                              @RequestParam(required = false) String orderId,
                              @RequestParam(required = false) String state,
                              @RequestParam(required = false) LocalDateTime min,
                              @RequestParam(required = false) LocalDateTime max){
        List<Wplan> searchlist = productionService.search(id,orderId,state,min,max);
        model.addAttribute("searchlist",searchlist);

        return "production/production-order";
    }
}
