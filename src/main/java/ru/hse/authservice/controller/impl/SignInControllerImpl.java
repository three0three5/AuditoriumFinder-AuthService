package ru.hse.authservice.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.authservice.controller.SignInController;
import ru.hse.authservice.dto.SignInRequest;
import ru.hse.authservice.dto.SignInTelegramRequest;
import ru.hse.authservice.dto.TokenResponse;

@RestController
public class SignInControllerImpl implements SignInController {
    @Override
    public ResponseEntity<TokenResponse> signIn(SignInRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<TokenResponse> signInTelegram(SignInTelegramRequest request) {
        return null;
    }
}
