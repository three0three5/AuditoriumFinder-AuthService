package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.orobtsovv.authservice.dto.request.SignUpRequest;
import ru.orobtsovv.authservice.dto.request.SignUpTelegramRequest;
import ru.orobtsovv.authservice.dto.response.TokenResponse;

@Tag(name = "Sign Up API", description = "Регистрация")
@RequestMapping("/signup")
public interface SignUpController {
    @Operation(description = "Регистрация пользователей, прошедших " +
            "верификацию электронной почты")
    @PostMapping("/")
    ResponseEntity<TokenResponse> signUp(@Valid @RequestBody SignUpRequest request);

    @Operation(description = "Регистрация пользователей, прошедших " +
            "верификацию эл. почты посредством телеграм бота. Если аккаунт пользователя " +
            "уже существует, будет осуществлен обычный вход, никнейм проигнорируется, " +
            "прошлая сессия telegram удалится")
    @PostMapping("/tg")
    ResponseEntity<TokenResponse> signUpTelegram(@Valid @RequestBody SignUpTelegramRequest request);
}
