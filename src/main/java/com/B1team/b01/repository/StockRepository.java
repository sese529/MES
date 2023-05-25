package com.B1team.b01.repository;


import com.B1team.b01.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {
    Stock findByMtrId(String mtrId);

    List<Object[]> findByProductIdNotNull();


    @Query("SELECT p.name, p.id, s.ea, s.unit, p.price, p.sort, s.location " +
            "FROM Product p JOIN Stock s ON p.id = s.productId " +
            "WHERE (:productName IS NULL OR p.name = :productName) " +
            "AND (:productId IS NULL OR p.id = :productId) " +
            "AND (:productSort IS NULL OR p.sort = :productSort)")
    List<Object[]> getProductStockList(@Param("productName") String productName,
                                       @Param("productId") String productId,
                                       @Param("productSort") String productSort);


    @Query("SELECT st.ea FROM Stock st WHERE st.productId = :productId")
    Long findByStockCnt(String productId);

    @Query("SELECT p.name, p.id, s.ea, s.unit, p.price, p.sort, s.location " +
            "FROM Product p JOIN Stock s ON p.id = s.productId " +
            "WHERE (:productName IS NULL OR p.name = :productName) " +
            "AND (:productId IS NULL OR p.id = :productId) " +
            "AND (:productSort IS NULL OR p.sort = :productSort)")
    List<Object[]> getAllList();


}
