package com.B1team.b01.repository;

import com.B1team.b01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    //vnaahr
    List<Product> findAll(); //전체 품목 리스트 가져오는거

}
