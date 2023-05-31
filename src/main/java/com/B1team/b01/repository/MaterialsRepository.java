package com.B1team.b01.repository;

import com.B1team.b01.entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MaterialsRepository extends JpaRepository<Materials, String> {

    List<Materials> findAll();

//    Optional<Materials> findById(@Param("id") String id);

}