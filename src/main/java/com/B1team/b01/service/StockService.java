package com.B1team.b01.service;

import com.B1team.b01.dto.PorderDto;
import com.B1team.b01.dto.StockDeleteDto;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    private final PinoutService pinoutService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;



    @Autowired
    public StockService(StockRepository stockRepository, PinoutService pinoutService) {
        this.stockRepository = stockRepository;
        this.pinoutService = pinoutService;
    }

    //시뮬레이션 - 재고확인 및 자동발주
    public Long stockCheck(String productId, String orderId) {
        //제품 잔여수량 확인

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

            Long remainingStock = orderCnt - stockEa; //주문량 - 재고량
            stock.setEa(remainingStock);
            stockRepository.save(stock);

            return remainingStock;

        } else if (stockEa >= orderCnt) {
            System.out.println("출하");

            Long remainingStock = stockEa - orderCnt; //재고량 - 주문량
            stock.setEa(remainingStock);
            stockRepository.save(stock);

            return remainingStock;

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
    public void createPorder(PorderDto pdto) {

        // 새로운 발주 등록 insert
        Porder porder = new Porder();

        porder.setId(generateId("POR", "porder_seq"));
        porder.setOrderDate(LocalDateTime.now()); // 현재일자

        porder.setCustomerId(pdto.getCustomerId()); // 거래처 고유번호
        porder.setCustomerName(pdto.getCustomerName()); // 발주처
        porder.setArrivalDate(pdto.getArrivalDate()); // 입고일
        porder.setCnt(pdto.getCnt());
        porder.setMtrUnit(pdto.getUnit());
        porder.setAmount(pdto.getAmount());
        porder.setState(pdto.getState());
        porder.setMtrId(pdto.getMtrId());
        porder.setMtrPrice(pdto.getPrice());
        porder.setMtrName(pdto.getName());

        porderRepository.save(porder);
    }

//
//    public List<StockListDto> getProductStockList(String productName, String productId, String productSort) {
//
//        List<Object[]> results = stockRepository.getProductStockList(productName, productId, productSort);
//
//        List<StockListDto> stockListDtoList = new ArrayList<>();
//
//        for (Object[] row : results) {
//            StockListDto stockListDto = new StockListDto();
//
//            // stock
//            stockListDto.setName(row[0].toString());
//            stockListDto.setProductId(row[1].toString());
////            stockListDto.setEa(Long.valueOf((Long) row[2]));
//            stockListDto.setUnit(row[2].toString());
//            stockListDto.setPrice(Long.valueOf((Long) row[3]));
//            stockListDto.setSort(row[4].toString());
//            stockListDto.setLocation(row[5].toString());
//
//            for (int i = 0; i <= 5; i++) {
//                System.out.println(i + ":" + row[i]);
//            }
//
//            stockListDtoList.add(stockListDto);
//
//        }
//        return stockListDtoList;
//    }

    // 재고 update
    public void updateStockEa(String mtrId, double stockEa) {
        Stock stock = stockRepository.findById(mtrId).orElse(null);
        if (stock != null) {
            stock.setEa((long) stockEa);
            stockRepository.save(stock);
        }
    }




    //제품 재고 업데이트(생산작업 들어갈 때 재고에서 빠지는 작업, kg단위로 빠짐(=이유: 재고현황이 kg으로 되어있어서))
    public void deleteStockEa(String productId){
        List<Long> stock = stockRepository.findByMTRStockCnt();
        Long[] oneBoxNeedProductResult = pinoutService.oneBoxNeedProduct(productId);  //출고수량


        switch(productId) {
            case "p21":
                StockDeleteDto stockDeleteDto = new StockDeleteDto();
                stockDeleteDto.setId("st1");
                stockDeleteDto.setLocation("창고1");
                stockDeleteDto.setMtrId("MTR36");
                stockDeleteDto.setUnit("g");
                stockDeleteDto.setEa(stock.get(0) - oneBoxNeedProductResult[0]);  //MTR36 양배추
                stockRepository.save(stockDeleteDto.toEntity());


                stockDeleteDto.setId("st6");
                stockDeleteDto.setLocation("창고6");
                stockDeleteDto.setMtrId("MTR41");
                stockDeleteDto.setUnit("ea");
                stockDeleteDto.setEa(stock.get(5) - oneBoxNeedProductResult[1]);  //MTR41 즙파우치
                stockRepository.save(stockDeleteDto.toEntity());


                //stockDeleteDto.setId("st1");
                //stockDeleteDto.setLocation("창고1");
                //stockDeleteDto.setMtrId("MTR36");
                //stockDeleteDto.setUnit("g");
                //stockDeleteDto.setEa(stock.get(0) - oneBoxNeedProductResult[2]);  //MTR43?박스  material이랑 stock이랑 창고위치 다름
                //stockRepository.save(stockDeleteDto.toEntity());                    //stock에 box 없음

            case "p22":
                StockDeleteDto stockDeleteDto1 = new StockDeleteDto();
                stockDeleteDto1.setId("st2");
                stockDeleteDto1.setLocation("창고2");
                stockDeleteDto1.setMtrId("MTR37");
                stockDeleteDto1.setUnit("g");
                stockDeleteDto1.setEa(stock.get(1) - oneBoxNeedProductResult[0]);  //MTR37 흑마늘
                stockRepository.save(stockDeleteDto1.toEntity());

                stockDeleteDto1.setId("st6");
                stockDeleteDto1.setLocation("창고6");
                stockDeleteDto1.setMtrId("MTR41");
                stockDeleteDto1.setUnit("ea");
                stockDeleteDto1.setEa(stock.get(5) - oneBoxNeedProductResult[1]);  //MTR41 즙파우치
                stockRepository.save(stockDeleteDto1.toEntity());

                //박스 상의하고 추가하기(*)

            case "p23":
                StockDeleteDto stockDeleteDto2 = new StockDeleteDto();
                stockDeleteDto2.setId("st3");
                stockDeleteDto2.setLocation("창고3");
                stockDeleteDto2.setMtrId("MTR38");
                stockDeleteDto2.setUnit("g");
                stockDeleteDto2.setEa(stock.get(2) - oneBoxNeedProductResult[0]);  //MTR38 석류농축액
                stockRepository.save(stockDeleteDto2.toEntity());

                stockDeleteDto2.setId("st5");
                stockDeleteDto2.setLocation("창고5");
                stockDeleteDto2.setMtrId("MTR40");
                stockDeleteDto2.setUnit("g");
                stockDeleteDto2.setEa(stock.get(4) - oneBoxNeedProductResult[1]);  //MTR40 콜라겐
                stockRepository.save(stockDeleteDto2.toEntity());

                stockDeleteDto2.setId("st7");
                stockDeleteDto2.setLocation("창고7");
                stockDeleteDto2.setMtrId("MTR42");
                stockDeleteDto2.setUnit("ea");
                stockDeleteDto2.setEa(stock.get(5) - oneBoxNeedProductResult[2]);  //MTR42 스틱파우치
                stockRepository.save(stockDeleteDto2.toEntity());

                //박스 상의하고 추가하기(*)

            case "p24":
                StockDeleteDto stockDeleteDto3 = new StockDeleteDto();
                stockDeleteDto3.setId("st4");
                stockDeleteDto3.setLocation("창고4");
                stockDeleteDto3.setMtrId("MTR39");
                stockDeleteDto3.setUnit("g");
                stockDeleteDto3.setEa(stock.get(3) - oneBoxNeedProductResult[0]);  //MTR39 매실농축액
                stockRepository.save(stockDeleteDto3.toEntity());

                stockDeleteDto3.setId("st5");
                stockDeleteDto3.setLocation("창고5");
                stockDeleteDto3.setMtrId("MTR40");
                stockDeleteDto3.setUnit("g");
                stockDeleteDto3.setEa(stock.get(4) - oneBoxNeedProductResult[1]);  //MTR40 콜라겐
                stockRepository.save(stockDeleteDto3.toEntity());

                stockDeleteDto3.setId("st7");
                stockDeleteDto3.setLocation("창고7");
                stockDeleteDto3.setMtrId("MTR42");
                stockDeleteDto3.setUnit("ea");
                stockDeleteDto3.setEa(stock.get(5) - oneBoxNeedProductResult[2]);  //MTR42 스틱파우치
                stockRepository.save(stockDeleteDto3.toEntity());

                //박스 상의하고 추가하기(*)

        }
    }



}