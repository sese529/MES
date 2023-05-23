package com.B1team.b01.repository;


import com.B1team.b01.dto.WperformDto;
import com.B1team.b01.dto.WplanDto;
import com.B1team.b01.entity.Wperform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface WperformRepository extends JpaRepository<Wperform, String> {



}
