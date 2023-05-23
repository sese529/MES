package com.B1team.b01.repository;

import com.B1team.b01.entity.Pinout;
import com.B1team.b01.entity.Stock;
import com.B1team.b01.entity.Worder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PinoutRepository extends JpaRepository<Pinout, String> {
}
