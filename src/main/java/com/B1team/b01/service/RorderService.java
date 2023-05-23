package com.B1team.b01.service;

import com.B1team.b01.dto.RorderDto;
import com.B1team.b01.entity.Rorder;
import com.B1team.b01.repository.RorderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RorderService {
    private final RorderRepository rorderRepository;

    public List<RorderDto> searchRorder(LocalDateTime startDate, LocalDateTime endDate, String orderId, String customerName, String productName, LocalDateTime startDeadline, LocalDateTime endDeadLine) {
        List<Rorder> rorderList = rorderRepository.findRordersByConditions(startDate, endDate, orderId, customerName, productName, startDeadline, endDeadLine);
        return RorderDto.of(rorderList);
    }
}
