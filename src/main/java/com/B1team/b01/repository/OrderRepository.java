package com.B1team.b01.repository;

import com.B1team.b01.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,String> {
    Optional<Order> findById(String id);
}
