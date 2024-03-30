package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.orobtsovv.authservice.dto.SignInRequest;
import ru.orobtsovv.authservice.dto.SignInTelegramRequest;
import ru.orobtsovv.authservice.dto.TokenResponse;

@Tag(name = "Sign In API")
@RequestMapping("/signin")
public interface SignInController {
    @Operation(summary = "Вход в аккаунт")
    @PostMapping
    ResponseEntity<TokenResponse> signIn(@Valid @RequestBody SignInRequest request);

    @Operation(summary = "Вход в аккаунт из телеграм бота",
            description = "Возможен только после верификации эл. почты и получения одноразового " +
                    "proof key. Если вход будет произведен с отличным telegram хендлом," +
                    " то он будет обновлен для аккаунта и профиля. Если доступ к UserService будет " +
                    "отсутствовать, вход не будет произведен")
    @PostMapping("/tg")
    ResponseEntity<TokenResponse> signInTelegram(@Valid @RequestBody SignInTelegramRequest request);
}
