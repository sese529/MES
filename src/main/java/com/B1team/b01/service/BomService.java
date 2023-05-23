package com.B1team.b01.service;

import com.B1team.b01.dto.NeedOrderDto;
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
    List<Stock> stockList = new ArrayList<>();

    public List<NeedOrderDto> calcBom(String pid, double amount){
        List<NeedOrderDto> needList =  new ArrayList<>();
        needList.clear();
        //재고 확인
        List<BOM> bomlist = bomRepository.findPID(pid);
        for(BOM b: bomlist) {
            String id = b.getMtrId();
            Stock stock = stockRepository.findByMtrId(id);
            stockList.add(stock);
        }
        double pouch = 0;
        double collagen = 0;
        double stickpouch = 0;
        System.out.println("--------------------"+stockList);

        //필요 용량 계산
        switch (pid){
            case "p5":
                double cabbage = (amount*80)*0.625f;
                pouch = amount;
                for(Stock s: stockList){
                    if(s.getMtrId().equals("MTR36")){
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR36");
                        ndto.setAmount(result(cabbage,s.getEa()));
                        needList.add(ndto);
                    } else if (s.getMtrId().equals("MTR41")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR41");
                        ndto.setAmount(result(pouch,s.getEa()));
                        needList.add(ndto);
                    }
                }
                break;
            case "p6":
                double g = (amount*20)*1.25f/3;
                pouch = amount;
                for(Stock s: stockList){
                    if(s.getMtrId().equals("MTR37")){
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR37");
                        ndto.setAmount(result(g,s.getEa()));
                        needList.add(ndto);
                    } else if (s.getMtrId().equals("MTR41")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR41");
                        ndto.setAmount(result(pouch,s.getEa()));
                        needList.add(ndto);
                    }
                }
                break;
            case "p7":
                double po = amount*5;
                collagen = amount*2;
                stickpouch = amount;
                for(Stock s: stockList){
                    if(s.getMtrId().equals("MTR38")){
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR38");
                        double re =result(po,s.getEa());
                        ndto.setAmount(re);
                        needList.add(ndto);
                    }
                    if (s.getMtrId().equals("MTR40")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR40");
                        double re = result(collagen,s.getEa());
                        ndto.setAmount(re);
                        needList.add(ndto);
                    }
                    if (s.getMtrId().equals("MTR42")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR42");
                        double re =result(stickpouch,s.getEa());
                        ndto.setAmount(re);
                        needList.add(ndto);
                    }
                }
                break;
            case "p8":
                double pl = amount*5;
                collagen = amount*2;
                stickpouch = amount;
                for(Stock s: stockList){
                    if(s.getMtrId().equals("MTR39")){
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR39");
                        ndto.setAmount(result(pl,s.getEa()));
                        needList.add(ndto);
                    } else if (s.getMtrId().equals("MTR40")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR40");
                        ndto.setAmount(result(collagen,s.getEa()));
                        needList.add(ndto);
                    }else if (s.getMtrId().equals("MTR42")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR42");
                        ndto.setAmount(result(stickpouch,s.getEa()));
                        needList.add(ndto);
                    }
                }
                break;
        }

        return needList;
    }

    public double result(double orderamount,double stockamount){
        double result = 0;

        if(orderamount > stockamount){
            result=orderamount-stockamount;
        }else if(stockamount>orderamount){
            result = 0;
        }

        return result;
    }

    //시뮬레이션 - 수주량(box) 매개변수 넣을 시 개별 개수 반환
    public double boxToAmount(long box) {

    }
}
