package com.B1team.b01.Service;

import com.B1team.b01.repository.WplanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WplanService {
    private final WplanRepository WplanRepository;


}
