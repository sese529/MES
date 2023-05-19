package com.B1team.b01;

import com.B1team.b01.Service.BomService;
import com.B1team.b01.dto.BomDto;
import com.B1team.b01.entity.BOM;
import com.B1team.b01.repository.BomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BomTest {
    @Autowired
    private BomService bomService;

    @Test
    void finaTest(){
        System.out.println(bomService.calcBom("p7",10));
    }
}
