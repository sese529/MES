package com.B1team.b01.repository;


import com.B1team.b01.entity.Worder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface WorderRepository extends JpaRepository<Worder, String> {
    //@Query("select wp from Wplan wp where wp.state ='완료' and wp.orderId = :orderId")
    //List<Worder> findWordersByConditions(LocalDateTime startDate, LocalDateTime endDate);//작업지시 리스트 중에 공정예약 or 공정이 돌아가고 있는 것
    //List<Worder> findWordersByConditions();//작업지시 리스트 중에 공정예약 or 공정이 돌아가고 있는 것

    @Query("select wo from Worder wo where wo.processId = :processId")
    List<Worder> findByState(String processId);

}
