package com.B1team.b01.service;

import com.B1team.b01.dto.PorderDto;
import com.B1team.b01.dto.StockDto;
import com.B1team.b01.dto.StockListDto;
import com.B1team.b01.entity.*;
import com.B1team.b01.repository.PorderRepository;
import com.B1team.b01.repository.RorderRepository;
import com.B1team.b01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    //시뮬레이션 - 재고확인 및 자동발주
    public Long stockCheck(String productId, String orderId) {
        //제품 잔여수량 확인
//        Optional<Stock> optional = stockRepository.findById(productId);
//        Stock stock = optional.get();

        Stock stock = (Stock) stockRepository.findByProductIdNotNull();

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

        Optional<Stock> stock = Optional.ofNullable((Stock) stockRepository.findByProductIdNotNull());

        if (stock.isPresent()) {
            Stock remainStock = stock.get();

            // 기존 재고 업데이트 로직 (기존 재고량에서 주문량만큼 수량 감소)
            remainStock.setEa(dto.getStockEa());
//            existingStock.setUnit(dto.getStockUnit());

//            stockRepository.save(remainStock);
        } else {
            //발주 계산

            // 새로운 발주 등록 로직
            Porder porder = new Porder();

            porder.setId(generateId("POR", "porder_seq"));
            porder.setAmount(pdto.getAmount());
            porder.setArrivalDate(pdto.getArrivalDate());
            porder.setCnt(pdto.getCnt());
            porder.setCustomerId(pdto.getCustomerId());
            porder.setCustomerName(pdto.getCustomerName());
            porder.setMtrId(pdto.getMtrId());
            porder.setMtrName(pdto.getName());
            porder.setMtrPrice(pdto.getPrice());
            porder.setMtrUnit(pdto.getUnit());
            porder.setOrderDate(pdto.getOrderDate());
            porder.setState(pdto.getState());

            porderRepository.save(porder);
        }
    }


    public List<StockListDto> getProductStockList(String productName, String productId, String productSort) {

        List<Object[]> results = stockRepository.getProductStockList(productName, productId, productSort);

        List<StockListDto> stockListDtoList = new ArrayList<>();

        for (Object[] row : results) {
            StockListDto stockListDto = new StockListDto();

            // stock
            stockListDto.setName(row[0].toString());
            stockListDto.setProductId(row[1].toString());
//            stockListDto.setEa(Long.valueOf((Long) row[2]));
            stockListDto.setUnit(row[2].toString());
            stockListDto.setPrice(Long.valueOf((Long) row[3]));
            stockListDto.setSort(row[4].toString());
            stockListDto.setLocation(row[5].toString());

            for(int i=0; i <= 5;i++){
                System.out.println(i+ ":" +row[i]);
            }

            stockListDtoList.add(stockListDto);

        }
        return stockListDtoList;
    }

}