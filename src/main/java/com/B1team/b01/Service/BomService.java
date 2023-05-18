package com.B1team.b01.Service;

import com.B1team.b01.dto.BomDto;
import com.B1team.b01.dto.StockDto;
import com.B1team.b01.entity.BOM;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.BomRepository;
import com.B1team.b01.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class BomService {
    private final BomRepository bomRepository;
    private final StockRepository stockRepository;
    HashMap<String, Long> stockmap = new HashMap<>();
    HashMap<String, Double> needmap = new HashMap<>();
    List<Stock> stockDtoList = new ArrayList<>();

    public HashMap<String, Double> calcBom(String pid, double amount){
        //재고 확인
        List<BOM> bomlist = bomRepository.findByProductId(pid);
        for(BOM b: bomlist) {
            String id = b.getMtrId();
            Stock stock = stockRepository.findByMtrId(id);
            stockDtoList.add(stock);
        }
        double pouch = 0;
        double collagen = 0;
        double stickpouch = 0;

        //필요 용량 계산
        switch (pid){
            case "p5":
                double cabbage = (amount*80)*0.625f;
                pouch = amount;
                needmap.put("MTR36",cabbage-stockmap.get("MTR36"));
                needmap.put("MTR41",pouch-stockmap.get("MTR41"));
            case "p6":
                double g = (amount*20)*1.25f/3;
                pouch = amount;
                needmap.put("MTR37",g-stockmap.get("MTR37"));
                needmap.put("MTR41",pouch-stockmap.get("MTR41"));
            case "p7":
                double po = amount*5;
                collagen = amount*2;
                stickpouch = amount;
                needmap.put("MTR38",po-stockmap.get("MTR38"));
                needmap.put("MTR40",collagen-stockmap.get("MTR40"));
                needmap.put("MTR42",stickpouch-stockmap.get("MTR42"));
            case "p8":
                double pl = amount*5;
                collagen = amount*2;
                stickpouch = amount;
                System.out.println(stockmap);
                needmap.put("MTR39",pl-stockmap.get("MTR39"));
                needmap.put("MTR40",collagen-stockmap.get("MTR40"));
                needmap.put("MTR42",stickpouch-stockmap.get("MTR42"));
        }

        return needmap;
    }
}
