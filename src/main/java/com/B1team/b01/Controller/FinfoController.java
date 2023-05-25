package com.B1team.b01.controller;

import com.B1team.b01.service.FinfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
public class FinfoController {
    private final FinfoService finfoService;
    //설비 현황
    @GetMapping("/facility/facility-status")
    public String facilityStatus(Model model){
        //설비명 목록
        List<String> nameList = finfoService.getFacilityNameList();
        model.addAttribute("nameList", nameList);
        return "facility/facility-status";
    }

    //설비 정보
    @GetMapping("/facility/facility-information")
    public String facilityInformation(Model model) {
        //설비명 목록
        List<String> nameList = finfoService.getFacilityNameList();
        model.addAttribute("nameList", nameList);

        //설비 위치 목록
        List<String> locationList = finfoService.getLocationList();
        model.addAttribute("locationList", locationList);
        return "facility/facility-information";
    }
}
