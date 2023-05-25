package com.B1team.b01.repository;


import com.B1team.b01.entity.Product;
import com.B1team.b01.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findByMtrId(String mtrId);

//    List<Stock> findByProductIdNotNull();
    List<Stock> findByProductIdNotNull();

    @Query("SELECT p FROM Product p WHERE p.id IN (SELECT s.productId FROM Stock s WHERE s.productId = :productId)")
    List<Product> findByProductIdInStock(@Param("productId") String productId);

    @Query("SELECT st.ea FROM Stock st WHERE st.productId = :productId")
    Long findByStockCnt(String productId);
}
