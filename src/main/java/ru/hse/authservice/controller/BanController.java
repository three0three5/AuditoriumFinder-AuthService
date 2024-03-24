package ru.hse.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Ban API", description = "Операции исключительно для UserService")
@RequestMapping("/ban")
public interface BanController {
    @Operation(summary = "Бан пользователя", description = "Удаляет аккаунт пользователя. " +
            "Если срок бана пользователя заканчивается, " +
            "пользователь может зарегистрироваться со своей почтой снова.")
    ResponseEntity<Void> banUser(@RequestParam int userid);

    @Operation(summary = "Удаление пользователя из забаненных",
            description = "Почта пользователя выносится из черного списка.")
    ResponseEntity<Void> unbanUser(@RequestParam String email);
}
