package ru.hse.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hse.authservice.dto.SignUpRequest;
import ru.hse.authservice.dto.SignUpTelegramRequest;
import ru.hse.authservice.dto.TokenResponse;

@Tag(name = "Sign Up API", description = "Регистрация")
@RequestMapping("/signup")
public interface SignUpController {
    @Operation(description = "Регистрация пользователей, прошедших " +
            "верификацию электронной почты")
    @PostMapping
    ResponseEntity<TokenResponse> signUp(@Valid @RequestBody SignUpRequest request);

    @Operation(description = "Регистрация пользователей, прошедших " +
            "верификацию эл. почты посредством телеграм бота. Если аккаунт пользователя " +
            "уже существует, к нему будет привязан telegram. Сервис автоматически отправит запрос " +
            "на включение телеграма в профиль, если это не удастся, регистрация не будет осуществлена")
    @PostMapping("/tg")
    ResponseEntity<TokenResponse> signUpTelegram(@Valid @RequestBody SignUpTelegramRequest request);
}
