package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.SignUpController;
import ru.orobtsovv.authservice.dto.request.SignUpRequest;
import ru.orobtsovv.authservice.dto.request.SignUpTelegramRequest;
import ru.orobtsovv.authservice.dto.response.TokenResponse;
import ru.orobtsovv.authservice.service.impl.SignService;

@RestController
@RequiredArgsConstructor
public class SignUpControllerImpl implements SignUpController {
    private final SignService service;

    @Override
    public ResponseEntity<TokenResponse> signUp(SignUpRequest request) {
        return ResponseEntity.ok(service.signUp(request));
    }

    @Override
    public ResponseEntity<TokenResponse> signUpTelegram(SignUpTelegramRequest request) {
        return ResponseEntity.ok(service.signUp(request));
    }
}
