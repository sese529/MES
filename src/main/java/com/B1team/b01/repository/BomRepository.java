package com.B1team.b01.repository;

import com.B1team.b01.dto.BomDto;
import com.B1team.b01.entity.BOM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BomRepository extends JpaRepository<BOM, String> {
    List<BOM> findByProductId(String pid);

}
