package com.B1team.b01.repository;

import com.B1team.b01.entity.Materials;
import com.B1team.b01.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {
    List<Stock> findByProductIdNotNull();
}
