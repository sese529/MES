package com.B1team.b01.repository;

import com.B1team.b01.entity.Finfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinfoRepository extends JpaRepository<Finfo, String> {
    List<Finfo> findByNameIn(List<String> name);
    List<Finfo> findByName(String name);

    //설비 정보 리스트
    @Query("SELECT f FROM Finfo f " +
            "WHERE (:name IS NULL OR f.name = :name) " +
            "AND (:location IS NULL OR f.location = :location ) ")
    List<Finfo> findFinfosByConditions(String name, String location);
}
