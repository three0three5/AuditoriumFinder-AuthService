package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Logout API", description = "Клиент при успешной операции обязан " +
        "удалить все хранимые у себя токены, ассоциированные с аккаунтом")
@RequestMapping("/logout")
public interface LogoutController {
    @Operation(summary = "Удаление всех сессий аккаунта", description = "Необходимо " +
            "передать refresh токен")
    @DeleteMapping("/all")
    ResponseEntity<Void> logoutAll(@RequestParam(name = "refresh_token") String refreshToken);

    @Operation(summary = "Удаление текущей сессии", description = "Передается refresh токен; " +
            "если токен уже был использован, logout производится для всех сессий")
    @DeleteMapping("/")
    ResponseEntity<Void> logout(@RequestParam(name = "refresh_token") String refreshToken);
}
