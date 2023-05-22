package com.B1team.b01.repository;

import com.B1team.b01.dto.WorderDto;
import com.B1team.b01.dto.WplanDto;
import com.B1team.b01.entity.Worder;
import com.B1team.b01.entity.Wplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WplanRepository extends JpaRepository<Wplan, String> {
    @Query("select new com.B1team.b01.dto.WplanDto(wp.orderId) from Wplan wp where wp.orderId = :orderId")
    WplanDto findByOrderId(@Param("orderId") String orderId);
}

