package com.B1team.b01.service;

import com.B1team.b01.entity.Finfo;
import com.B1team.b01.repository.FinfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
//@Service
@Component
public class FinfoService {
    private final FinfoRepository finfoRepository;

    //설비명 목록 받기
    public List<String> getFacilityNameList() {
        List<Finfo> finfoList = finfoRepository.findAll();
        Set<String> set = new HashSet<>();
        for(Finfo info : finfoList) {
            set.add(info.getName());
        }
        return new ArrayList<>(set);
    }

    //설비 위치 목록 받기
    public List<String> getLocationList() {
        List<Finfo> finfoList = finfoRepository.findAll();
        Set<String> set = new HashSet<>();
        for(Finfo info : finfoList) {
            set.add(info.getLocation());
        }
        return new ArrayList<>(set);
    }
}
