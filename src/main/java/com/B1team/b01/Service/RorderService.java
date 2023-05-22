package com.B1team.b01.Service;

import com.B1team.b01.repository.RorderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RorderService {
    private final RorderRepository rorderRepository;

}
