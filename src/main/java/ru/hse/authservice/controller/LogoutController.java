package ru.hse.authservice.controller;

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
    @Operation(summary = "Удаление всех сессий аккаунта")
    @DeleteMapping("/all")
    ResponseEntity<Void> logoutAll(@RequestParam String jwt);

    @Operation(summary = "Удаление текущей сессии")
    @DeleteMapping
    ResponseEntity<Void> logout(@RequestParam String jwt);
}
