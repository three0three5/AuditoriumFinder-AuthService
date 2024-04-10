package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.SignInController;
import ru.orobtsovv.authservice.dto.request.SignInRequest;
import ru.orobtsovv.authservice.dto.request.SignInTelegramRequest;
import ru.orobtsovv.authservice.dto.response.TokenResponse;
import ru.orobtsovv.authservice.service.impl.SignService;

@RestController
@RequiredArgsConstructor
public class SignInControllerImpl implements SignInController {
    private final SignService signService;

    @Override
    public ResponseEntity<TokenResponse> signIn(SignInRequest request) {
        return ResponseEntity.ok(signService.signIn(request));
    }

    @Override
    public ResponseEntity<TokenResponse> signInTelegram(SignInTelegramRequest request) {
        return ResponseEntity.ok(signService.signInTg(request));
    }
}
