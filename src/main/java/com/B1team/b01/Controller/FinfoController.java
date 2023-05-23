package com.B1team.b01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@Transactional
@RequiredArgsConstructor
public class FinfoController {
    //설비 현황
    @GetMapping("/facility/facility-status")
    public String facilityStatus() {
        return "facility/facility-status";
    }

    //설비 정보
    @GetMapping("/facility/facility-information")
    public String facilityInformation() {
        return "facility/facility-information";
    }
}
