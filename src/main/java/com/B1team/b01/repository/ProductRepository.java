package com.B1team.b01.repository;

import com.B1team.b01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {


}
