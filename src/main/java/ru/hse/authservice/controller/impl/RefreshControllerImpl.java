package ru.hse.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.authservice.controller.RefreshController;
import ru.hse.authservice.domain.repository.RefreshRepository;
import ru.hse.authservice.dto.TokenResponse;
import ru.hse.authservice.service.impl.JwtService;

@RestController
@RequiredArgsConstructor
public class RefreshControllerImpl implements RefreshController {
    private final JwtService jwtService;
    private final RefreshRepository refreshRepository;

    @Override
    public ResponseEntity<TokenResponse> refreshToken(String token) {
        throw new NotImplementedException();
    }
}
