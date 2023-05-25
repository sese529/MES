package com.B1team.b01.service;

import com.B1team.b01.dto.FinfoDto;
import com.B1team.b01.entity.Finfo;
import com.B1team.b01.entity.Worder;
import com.B1team.b01.repository.FinfoRepository;
import com.B1team.b01.repository.WorderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
//@Service
@Component
public class FinfoService {
    private final FinfoRepository finfoRepository;
    private final WorderRepository worderRepository;

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

    

    //현재 가동 중인 설비인지 확인(반환값 true: 가동중, faluse: 미가동)
    public boolean isRunning(String facilityId, LocalDateTime currentTime) {
        List<Worder> list = worderRepository.findWordersByFacilityIdAndCurrentDateTime(facilityId, LocalDateTime.now());
        return !list.isEmpty();
    }

    public List<FinfoDto> getSearchList(String name, String location) {
        List<Finfo> list = finfoRepository.findFinfosByConditions(name, location);
        return FinfoDto.of(list);
    }
}
