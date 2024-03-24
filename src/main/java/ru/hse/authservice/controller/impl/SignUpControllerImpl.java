package ru.hse.authservice.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.authservice.controller.SignUpController;
import ru.hse.authservice.dto.SignUpRequest;
import ru.hse.authservice.dto.SignUpTelegramRequest;
import ru.hse.authservice.dto.TokenResponse;

@RestController
public class SignUpControllerImpl implements SignUpController {
    @Override
    public ResponseEntity<TokenResponse> signUp(SignUpRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<TokenResponse> signUpTelegram(SignUpTelegramRequest request) {
        return null;
    }
}
