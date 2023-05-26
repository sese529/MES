package com.B1team.b01.service;

import com.B1team.b01.dto.PorderOutputDto;
import com.B1team.b01.dto.StockDto;
import com.B1team.b01.entity.Porder;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.PorderDetailRepository;
import com.B1team.b01.repository.PorderRepository;
import com.B1team.b01.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PorderService {
    private final PorderRepository porderRepository;
    private final PorderDetailRepository porderDetailRepository;
    private final StockRepository stockRepository;

    //발주 등록
    public void createPorder(StockDto stockDto) {

        //주문량 > 재고량, 자동 발주

        Optional<Stock> stock;
        stock = Optional.ofNullable((Stock) stockRepository.findByProductIdNotNull());
        if (stock.isPresent()) {
            Stock existingStock = stock.get();
            // 기존 재고 업데이트 로직
            existingStock.setEa(stockDto.getStockEa());
            existingStock.setUnit(stockDto.getStockUnit());
            stockRepository.save(existingStock);
        } else {
            // 새로운 발주 등록 로직
            Stock newStock = new Stock();
            newStock.setId("12345");
            newStock.setProductId(stockDto.getProductId());
            newStock.setMtrId(stockDto.getMtrId());
            newStock.setLocation("Warehouse");
            newStock.setEa(stockDto.getStockEa());
            newStock.setUnit(stockDto.getStockUnit());
            stockRepository.save(newStock);

        }
    }


    //발주 현황 - 검색 시 Entity - Dto 변환
    public List<PorderOutputDto> getPorderList(LocalDateTime startDate,
                                               LocalDateTime endDate,
                                               String mtrName,
                                               String customerName,
                                               String state,
                                               LocalDateTime currentTime,
                                               LocalDateTime startArrivalDate,
                                               LocalDateTime endArrivalDate) {
        List<Porder> porders = porderRepository.findPordersByConditons(startDate, endDate, mtrName, customerName, state, currentTime, startArrivalDate, endArrivalDate);
        return PorderOutputDto.of(porders);
    }

}
