package com.B1team.b01.repository;

import com.B1team.b01.entity.LOT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<LOT, String> {

    //작업계획 등록 관련 같은 수주번호로 선 등록 계획이 있는지 확인하기
    //@Query("select wp from Wplan wp where wp.orderId = :orderId")
    //Optional<Wplan> findByPlanOrderId(String orderId);


}

