package com.B1team.b01.repository;

import com.B1team.b01.entity.Rorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RorderRepository extends JpaRepository<Rorder, String> {
    @Query("SELECT r FROM Rorder r " +
            "WHERE (:startDate IS NULL OR r.date >= :startDate) " +
            "AND (:endDate IS NULL OR r.date <= :endDate) " +
            "AND (:orderId IS NULL OR r.id = :orderId) " +
            "AND (:customerName IS NULL OR r.customerName = :customerName) " +
            "AND (:productName IS NULL OR r.productName = :productName) " +
            "AND (:startDeadline IS NULL OR r.deadline >= :startDeadline) " +
            "AND (:endDeadLine IS NULL OR r.deadline <= :endDeadLine) " +
            "ORDER BY r.date DESC")
    List<Rorder> findRordersByConditions(LocalDateTime startDate, LocalDateTime endDate, String orderId, String customerName, String productName, LocalDateTime startDeadline, LocalDateTime endDeadLine);

}
