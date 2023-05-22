package com.B1team.b01.repository;

import com.B1team.b01.entity.Finfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinfoRepository extends JpaRepository<Finfo, String> {
    List<Finfo> findByNameIn(List<String> name);
    List<Finfo> findByName(String name);
}
