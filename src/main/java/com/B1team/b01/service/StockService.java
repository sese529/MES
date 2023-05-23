package com.B1team.b01.service;

import com.B1team.b01.dto.PorderDto;
import com.B1team.b01.dto.StockDto;
import com.B1team.b01.entity.Porder;
import com.B1team.b01.entity.Rorder;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.PorderRepository;
import com.B1team.b01.repository.RorderRepository;
import com.B1team.b01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.HTMLDocument;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service

public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private RorderRepository rorderRepository;

    @Autowired
    private PorderRepository porderRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    //시뮬레이션 - 재고확인 및 자동발주
    public Long stockCheck (String productId, String orderId) {
        //제품 잔여수량 확인
//        Optional<Stock> optional = stockRepository.findById(productId);
//        Stock stock = optional.get();

        Stock stock = stockRepository.findByProductId(productId);

        //재고 수량

        Long stockEa = stock.getEa();


        System.out.println("stockEa" + stockEa);

//      수주 주문 수량
        Optional<Rorder> optionalOne = rorderRepository.findById(orderId);
        Rorder order = optionalOne.get();

        //주문량
        Long orderCnt = order.getCnt();

        System.out.println("orderCnt" + orderCnt);

        // 재고량 & 주문량 비교
        if (stockEa < orderCnt) {
            //재고량이 주문량보다 적으면 자동발주 계산
            System.out.println("발주계산으로 넘어감");

            return stockEa;

        } else if (stockEa >= orderCnt) {
            System.out.println("출하");
            return orderCnt - stockEa;

        } else {
            return null;
        }
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
    public void createPorder(StockDto dto, PorderDto pdto) {

        //주문량 > 재고량, 자동 발주

        Optional<Stock> stock = Optional.ofNullable(stockRepository.findByProductId(dto.getProductId()));

        if (stock.isPresent()) {
            Stock remainStock = stock.get();

            // 기존 재고 업데이트 로직 (기존 재고량에서 주문량만큼 수량 감소)
            remainStock.setEa(dto.getStockEa());
//            existingStock.setUnit(dto.getStockUnit());
            stockRepository.save(remainStock);
        } else {
            //발주 계산



            // 새로운 발주 등록 로직
            Porder porder = new Porder();

            porder.setId(generateId("POR","porder_seq"));
            porder.setAmount(pdto.getAmount());
            porder.setArrivalDate(pdto.getArrivaldate());
            porder.setCnt(pdto.getCnt());
            porder.setCustomerId(pdto.getCustomerId());
            porder.setCustomerName(pdto.getCustomerName());
            porder.setMtrId(pdto.getMtrId());
            porder.setMtrName(pdto.getName());
            porder.setMtrPrice(pdto.getPrice());
            porder.setMtrUnit(pdto.getUnit());
            porder.setOrderDate(pdto.getOrderdate());
            porder.setState(pdto.getState());

            porderRepository.save(porder);

        }
    }
    
//    제품재고 조회
//    public List<Stock> getProductStock() {
//
//    }
}
