package com.B1team.b01.repository;

import com.B1team.b01.entity.Rorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RorderRepository extends JpaRepository<Rorder, String> {
    @Query("SELECT r FROM Rorder r " +
            "WHERE (:startDate IS NULL OR r.date >= :startDate) " +
            "AND (:endDate IS NULL OR r.date <= :endDate) " +
            "AND (:orderId IS NULL OR r.id = :orderId) " +
            "AND (:customerName IS NULL OR r.customerName = :customerName)")
    List<Rorder> findRordersByConditions(LocalDate startDate, LocalDate endDate, String orderId, String customerName);

}
