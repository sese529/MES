package com.B1team.b01.repository;

import com.B1team.b01.entity.Porder;
import com.B1team.b01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorderRepository extends JpaRepository<Porder,String> {


}
