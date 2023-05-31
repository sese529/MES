package com.B1team.b01.service;

import com.B1team.b01.dto.NeedOrderDto;
import com.B1team.b01.dto.PorderOutputDto;
import com.B1team.b01.entity.Customer;
import com.B1team.b01.entity.Materials;
import com.B1team.b01.entity.Porder;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.PorderDetailRepository;
import com.B1team.b01.repository.PorderRepository;
import com.B1team.b01.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class PorderService {
    private final PorderRepository porderRepository;
    private final PorderDetailRepository porderDetailRepository;
    private final StockRepository stockRepository;
    private final PorderDetailService porderDetailService;
    private EntityManagerFactory entityManagerFactory;
    private BomService bomService;
    private MaterialsService materialsService;
    private CustomerService customerService;


    //발주 현황 - 검색 시 Entity - Dto 변환
    public List<PorderOutputDto> getPorderList(String start,
                                               String end,
                                               String mtrName,
                                               String customerName,
                                               String state,
                                               String startArriva,
                                               String endArrival) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDate = start == null || "".equals(start)? null : LocalDate.parse(start, formatter).atStartOfDay();
        LocalDateTime endDate = end == null || "".equals(end) ? null : LocalDate.parse(end, formatter).atTime(23, 59, 59);
        LocalDateTime startArrivalDate = start == null || "".equals(startArriva)? null : LocalDate.parse(startArriva, formatter).atStartOfDay();
        LocalDateTime endArrivalDate = end == null || "".equals(endArrival) ? null : LocalDate.parse(endArrival, formatter).atTime(23, 59, 59);

        List<Porder> porders = porderRepository.findPordersByConditons(startDate, endDate, mtrName, customerName, state, LocalDateTime.now(), startArrivalDate, endArrivalDate);
        return PorderOutputDto.of(porders);
    }


    //문자열 시퀀스 메소드
    @Transactional
    public String generateId(String head, String seqName) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT " + seqName + ".NEXTVAL FROM dual").getSingleResult();
        String id = head + sequenceValue;
        return id;
    }

    //발주 등록
    public void createPorder(LocalDateTime date, String productId, Long cnt) {

        List<NeedOrderDto> bomList = bomService.calcBom(productId,cnt);

        for (NeedOrderDto d : bomList) {
            LocalDateTime caldate = materialsService.calculateArrivalDate(date,d.getMtrId());

            Materials mtrId = materialsService.getMaterialByMtrId(d.getMtrId()).get();

            //거래처 테이블과 원자재 테이블 공통된 컬럼이 없어 랜덤으로 거래처코드 & 코드 추출
            List<Customer> porderList = customerService.getPorderList("발주처");
            if (!porderList.isEmpty()) {
                Random random = new Random();
                Customer randomCustomer = porderList.get(random.nextInt(porderList.size()));
                String randomCustomerId = randomCustomer.getId();

                String customerName = String.valueOf(customerService.getPorderCustomer("발주처",randomCustomerId));

                // 새로운 발주 등록 insert
                Porder porder = new Porder();

                porder.setId(generateId("POR", "porder_seq"));
                porder.setOrderDate(date); // 수주일
                porder.setCustomerId(randomCustomerId); // 거래처 고유번호
                porder.setCustomerName(customerName); // 발주처
                porder.setArrivalDate(caldate); // 입고일 동진님
                porder.setCnt(d.getAmount()); // 세윤님
                porder.setMtrUnit(mtrId.getUnit()); //d.getMtrId()

                double price = d.getAmount() * mtrId.getPrice();
                porder.setAmount(price);
                porder.setState("예정");
                porder.setMtrId(d.getMtrId());
                porder.setMtrPrice(mtrId.getPrice());
                porder.setMtrName(mtrId.getName());

                porderRepository.save(porder);

                //상세 발주
                porderDetailService.addPorderDetail(porder.getId(),porder.getMtrId(),porder.getCnt(),porder.getMtrPrice());

            }
        }
    }

}
