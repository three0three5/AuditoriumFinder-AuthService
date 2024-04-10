package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.RoleAccessController;
import ru.orobtsovv.authservice.dto.request.ClientCredentialsRequest;
import ru.orobtsovv.authservice.dto.response.JwtTokenResponse;
import ru.orobtsovv.authservice.dto.response.TokenResponse;
import ru.orobtsovv.authservice.service.impl.AccessService;

@RestController
@RequiredArgsConstructor
public class RoleAccessControllerImpl implements RoleAccessController {
    private final AccessService service;

    @Override
    public ResponseEntity<TokenResponse> getAccessForModerator(ClientCredentialsRequest request) {
        return ResponseEntity.ok(service.moderator(request));
    }

    @Override
    public ResponseEntity<JwtTokenResponse> getAccessForTelegramBot(ClientCredentialsRequest request) {
        return ResponseEntity.ok(service.telegram(request));
    }
}
