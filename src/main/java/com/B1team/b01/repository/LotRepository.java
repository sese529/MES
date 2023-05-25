package com.B1team.b01.repository;

import com.B1team.b01.entity.Finprod;
import com.B1team.b01.entity.LOT;
import com.B1team.b01.entity.Wplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LotRepository extends JpaRepository<LOT, String> {

    //계획테이블 불러와서 dto에 값 넣기
    @Query("select wp from Wplan wp where wp.id = :wplanId and wp.productId = :productId")
    Wplan findByPlanOrderId(String wplanId);

    @Query("select fin from Finprod fin where fin.productId = :productId")
    Finprod findByFinprodId(String productId);


}

