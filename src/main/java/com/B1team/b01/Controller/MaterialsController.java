package com.B1team.b01.Controller;

import com.B1team.b01.dto.MaterialStockDto;
import com.B1team.b01.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/materials")
public class MaterialsController {
    private final StockRepository stockRepository;
    //발주 현황
    @GetMapping("/porder-status")
    public String placeAnOrderStatus() {
        return "/materials/porder-status";
    }

    //자재 입출 현황
    @GetMapping("/material-inoutput")
    public String materialInOutPut() {
        return "/materials/material-inoutput";
    }

    //자재 재고 현황
    @GetMapping("/material-inventory")
    public String materialInventory(Model model, String mtrName) {
        List<MaterialStockDto> list = stockRepository.findMaterialStock(mtrName);
        model.addAttribute("list", list);
        return "/materials/material-inventory";
    }
}
