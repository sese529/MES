package com.B1team.b01.repository;


import com.B1team.b01.dto.StockDto;
import com.B1team.b01.dto.StockListDto;
import com.B1team.b01.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findByMtrId(String mtrId);
    List<StockDto> findByProductIdNotNull();

    @Query("SELECT p.name, p.id, s.ea, s.unit, p.price, p.sort, s.location FROM Product p JOIN Stock s ON p.id = s.productId")
    List<Object[]> getProductStockList();

}
