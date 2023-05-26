package com.B1team.b01.repository;

import com.B1team.b01.dto.WplanDto;
import com.B1team.b01.entity.Wplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WplanRepository extends JpaRepository<Wplan, String> {

    //작업계획 등록 관련, 같은 수주번호로 먼저 등록된 작업계획이 있는지 확인하기
    @Query("select wp from Wplan wp where wp.orderId = :orderId")
    Optional<Wplan> findByPlanOrderId(String orderId);

    //작업계획 등록 관련, 같은 수주번호로 먼저 등록된 작업계획이 있는지 확인하기
    @Query("select wp from Wplan wp where wp.orderId = :orderId")
    Wplan findByPlanOrderId2(String orderId);

    //작업지시 관련 작업계획 조회하기
    @Query("select wp from Wplan wp where wp.orderId = :orderId and wp.state = '진행대기'")
    WplanDto findByOrderId(@Param("orderId") String orderId);

    //작업실적 관련 작업계획 조회하기
    @Query("select wp from Wplan wp where wp.state ='완료' and wp.orderId = :orderId")
    List<Wplan> findByWplanState(@Param("orderId") String orderId);

}

