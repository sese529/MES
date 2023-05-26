package com.B1team.b01.service;

import com.B1team.b01.dto.NeedEaDto;
import com.B1team.b01.dto.NeedOrderDto;
import com.B1team.b01.entity.BOM;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.repository.BomRepository;
import com.B1team.b01.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BomService {
    @Autowired
    private final BomRepository bomRepository;
    @Autowired
    private final StockRepository stockRepository;
    @Autowired
    private final StockService stockService;


    public List<NeedOrderDto> calcBom(String pid, double box){
        List<Stock> stockList = new ArrayList<>();
        List<NeedOrderDto> needList =  new ArrayList<>();
        double amount = boxToAmount(pid, (long) box);
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
            case "p21":
                double cabbage = (amount*80)*0.625f;
                pouch = amount;
                for(Stock s: stockList){
                    if(s.getMtrId().equals("MTR36")){
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR36");
                        ndto.setAmount(result("MTR36",cabbage,s.getEa()));
                        needList.add(ndto);
                    } else if (s.getMtrId().equals("MTR41")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR41");
                        ndto.setAmount(result("MTR41",pouch,s.getEa()));
                        needList.add(ndto);
                    }
                }
                break;
            case "p22":
                double g = (amount*20)*1.25f/3;
                pouch = amount;
                for(Stock s: stockList){
                    if(s.getMtrId().equals("MTR37")){
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR37");
                        ndto.setAmount(result("MTR37",g,s.getEa()));
                        needList.add(ndto);
                    } else if (s.getMtrId().equals("MTR41")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR41");
                        ndto.setAmount(result("MTR41",pouch,s.getEa()));
                        needList.add(ndto);
                    }
                }
                break;
            case "p23":
                double po = amount*5;
                collagen = amount*2;
                stickpouch = amount;
                for(Stock s: stockList){
                    if(s.getMtrId().equals("MTR38")){
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR38");
                        double re =result("MTR38",po,s.getEa());
                        ndto.setAmount(re);
                        needList.add(ndto);
                    }
                    if (s.getMtrId().equals("MTR40")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR40");
                        double re = result("MTR40",collagen,s.getEa());
                        ndto.setAmount(re);
                        needList.add(ndto);
                    }
                    if (s.getMtrId().equals("MTR42")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR42");
                        double re =result("MTR42",stickpouch,s.getEa());
                        ndto.setAmount(re);
                        needList.add(ndto);
                    }
                }
                break;
            case "p24":
                double pl = amount*5;
                collagen = amount*2;
                stickpouch = amount;
                for(Stock s: stockList){
                    if(s.getMtrId().equals("MTR39")){
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR39");
                        ndto.setAmount(result("MTR39",pl,s.getEa()));
                        needList.add(ndto);
                    } else if (s.getMtrId().equals("MTR40")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR40");
                        ndto.setAmount(result("MTR40",collagen,s.getEa()));
                        needList.add(ndto);
                    }else if (s.getMtrId().equals("MTR42")) {
                        NeedOrderDto ndto = new NeedOrderDto();
                        ndto.setMtrId("MTR42");
                        ndto.setAmount(result("MTR42",stickpouch,s.getEa()));
                        needList.add(ndto);
                    }
                }
                break;
        }

        return needList;
    }

    //시뮬레이션 - 필요 단위량 계산
    public NeedEaDto calcEa(String pid, long box){
        NeedEaDto needEa = new NeedEaDto();
        double amount = boxToAmount(pid, box);
        needEa.setBox(box);
        needEa.setAmount(amount);

        //필요 용량 계산
        switch (pid){
            case "p21":
                needEa.setMaterialWeight((amount*80)*0.625f);
                needEa.setLiquidWeight(amount*80);
                break;
            case "p22":
                needEa.setMaterialWeight((amount*20)*1.25f/3);
                needEa.setLiquidWeight(amount*20);
                break;
            case "p23": case "p24":
                needEa.setLiquidWeight(amount*5);
                break;
        }
        return needEa;
    }

    public double result(String mtr_id,double orderamount,double stockamount){
        double result = 0;

        if(orderamount > stockamount){
            result=orderamount-stockamount;
        }else if(stockamount>orderamount){
            result = 0;
            double change = stockamount-orderamount;
            stockService.updateStockEa(mtr_id,change);
        }
        return result;
    }

    //시뮬레이션 - 수주량(box) 매개변수 넣을 시 개별 개수 반환
    public double boxToAmount(String pid, long box) {
        double unit = pid.equals("p21") || pid.equals("p22") ? 30 : 25;
        return box * unit;
    }
}
