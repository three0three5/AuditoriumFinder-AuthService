package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.orobtsovv.authservice.dto.BanRequest;

@Tag(name = "Ban API", description = "Операции исключительно для UserService")
@RequestMapping("/banned")
public interface BanController {
    @Operation(summary = "Бан пользователя", description = "Удаляет аккаунт пользователя. " +
            "Если срок бана пользователя заканчивается, " +
            "пользователь может зарегистрироваться со своей почтой снова.")
    @PostMapping("/add")
    ResponseEntity<Void> banUser(@Valid @RequestBody BanRequest banRequest);

    @PostMapping("/delete")
    @Operation(summary = "Удаление пользователя из забаненных",
            description = "Почта пользователя выносится из черного списка.")
    ResponseEntity<Void> unbanUser(@RequestParam String email, @RequestParam int moderatorId);
}
