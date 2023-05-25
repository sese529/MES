package com.B1team.b01.service;

import com.B1team.b01.dto.RorderDto;
import com.B1team.b01.dto.RorderFormDto;
import com.B1team.b01.entity.Rorder;
import com.B1team.b01.repository.RorderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RorderService {
    private final RorderRepository rorderRepository;
    private final MprocessService mprocessService;
    private final EntityManager entityManager;

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

    //수주 등록 - 예정 납기일 예측을 위한 매개변수 변환
    public String calculateOrderDeliveryDate(String orderDateStr, String productId, String orderCntStr) {
        // String 타입 24시간제 형태 orderDateStr을 LocalDateTime 타입 orderDate으로 변환
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
        LocalDateTime orderDate = LocalDateTime.parse(orderDateStr, inputFormatter);

        // 예정 납기일 받아서 String 형으로 반환
        LocalDateTime deliveryDate = mprocessService.caluculateDeadline(orderDate, productId, Long.parseLong(orderCntStr));
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a HH:mm", Locale.ENGLISH);
        String deliveryStr = deliveryDate.format(outputFormatter).replace("AM", "오전").replace("PM", "오후");

        return deliveryStr;
    }

    //수주 등록
    public void addRorder(RorderFormDto dto) {
        dto.setId(generateId("ROD", "order_seq"));  //id 세팅
        rorderRepository.save(dto.toEntity());  //save하기
    }

    //id 지정하는 메소드
    @Transactional
    public String generateId(String head, String seqName) {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT " + seqName + ".NEXTVAL FROM dual").getSingleResult();
        String id = head + sequenceValue;
        return id;
    }

    //수주 정보 받아오기
    public RorderDto findById(String rid) {
        Optional<Rorder> optional = rorderRepository.findById(rid);
        return RorderDto.of(optional.get());
    }
}
