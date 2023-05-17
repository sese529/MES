package com.B1team.b01.repository;

import com.B1team.b01.entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialsRepository extends JpaRepository<Materials, Long> {
}
