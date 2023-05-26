package com.B1team.b01.repository;

import com.B1team.b01.entity.Finprod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinprodRepository extends JpaRepository<Finprod, String> {


}
