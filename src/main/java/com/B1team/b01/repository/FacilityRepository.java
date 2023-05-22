package com.B1team.b01.repository;

import com.B1team.b01.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, String> {
    List<Facility> findByNameIn(List<String> name);
    List<Facility> findByName(String name);
}
