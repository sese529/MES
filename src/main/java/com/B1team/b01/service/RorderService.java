package com.B1team.b01.service;

import com.B1team.b01.dto.RorderDto;
import com.B1team.b01.entity.Rorder;
import com.B1team.b01.repository.RorderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RorderService {
    private final RorderRepository rorderRepository;

    public List<RorderDto> searchRorder(String start, String end, String orderId, String state, String customerName, String productName, String startLine, String endLine) {
        //날짜 관련 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDate = start == null || "".equals(start)? null : LocalDate.parse(start, formatter).atStartOfDay();
        LocalDateTime endDate = end == null || "".equals(end) ? null : LocalDate.parse(end, formatter).atTime(23, 59, 59);
        LocalDateTime startDeadline = startLine == null || "".equals(startLine) ? null : LocalDate.parse(startLine, formatter).atStartOfDay();
        LocalDateTime endDeadLine = endLine == null || "".equals(endLine) ? null : LocalDate.parse(endLine, formatter).atTime(23, 59, 59);

        LocalDateTime now = null;
        if(state != null && (state.equals("진행중") || state.equals("완료")))
            now = LocalDateTime.now();

        List<Rorder> rorderList = rorderRepository.findRordersByConditions(startDate, endDate, orderId, state, now, customerName, productName, startDeadline, endDeadLine);
        return RorderDto.of(rorderList);
    }
}
