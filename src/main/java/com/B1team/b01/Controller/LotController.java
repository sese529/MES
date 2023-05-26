package com.B1team.b01.controller;

import com.B1team.b01.dto.BomListDto;
import com.B1team.b01.entity.Customer;
import com.B1team.b01.entity.LOT;
import com.B1team.b01.entity.Materials;
import com.B1team.b01.entity.Product;
import com.B1team.b01.service.BomService;
import com.B1team.b01.service.LotService;
import com.B1team.b01.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/lot/*")
public class LotController {

    @Autowired
    private LotService lotService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;



    //LOT리스트 표출
    @GetMapping("/lot")
    public String lotList(Model model) {
        model.addAttribute("lotList", lotService.getLotList());

        return "/lot/lot";
    }

    //검색
    @GetMapping("/lotsearch")
    public String lotSearch(Model model,
                            @RequestParam(required = false) String id,
                            @RequestParam(required = false) String processId,
                            @RequestParam(required = false) String productId,
                            @RequestParam(required = false) LocalDateTime min,
                            @RequestParam(required = false) LocalDateTime max){

        if("".equals(id)){
            id=null;
        }
        if("".equals(processId)){
            processId=null;
        }
        if("".equals(productId)){
            productId=null;
        }
        if("".equals(min)){
            min=null;
        }
        if("".equals(max)){
            max=null;
        }

        System.out.println(id);
        System.out.println(processId);
        System.out.println(productId);
        System.out.println(min);
        System.out.println(max);

        List<LOT> slist = lotService.search(id,processId,productId,min,max);
        System.out.println(slist);
        model.addAttribute("slist",slist);

        return "/lot/lot";
    }


}