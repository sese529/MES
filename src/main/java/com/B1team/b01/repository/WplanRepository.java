package com.B1team.b01.repository;

import com.B1team.b01.dto.WplanDto;
import com.B1team.b01.entity.Wplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WplanRepository extends JpaRepository<Wplan, String> {
    //작업지시 관련 작업계획 조회하기
    @Query("select new com.B1team.b01.dto.WplanDto(wp.orderId) from Wplan wp where wp.orderId = :orderId")
    WplanDto findByOrderId(@Param("orderId") String orderId);

    //작업실적 관련 작업계획 조회하기
    @Query("select wp from Wplan wp where wp.state ='완료' and wp.orderId = :orderId")
    List<Wplan> findByWplanState(@Param("orderId") String orderId);
}

