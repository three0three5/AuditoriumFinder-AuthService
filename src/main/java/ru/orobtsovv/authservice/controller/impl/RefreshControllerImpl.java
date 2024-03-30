package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.RefreshController;
import ru.orobtsovv.authservice.domain.repository.RefreshRepository;
import ru.orobtsovv.authservice.dto.TokenResponse;
import ru.orobtsovv.authservice.service.impl.JwtService;
import ru.orobtsovv.authservice.service.impl.SessionService;

@RestController
@RequiredArgsConstructor
public class RefreshControllerImpl implements RefreshController {
    private final SessionService sessionService;

    @Override
    public ResponseEntity<TokenResponse> refreshToken(String token) {
        return ResponseEntity.ok(sessionService.refresh(token));
    }
}
