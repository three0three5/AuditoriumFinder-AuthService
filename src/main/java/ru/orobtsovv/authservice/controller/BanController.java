package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.orobtsovv.authservice.dto.response.BannedEmailResponse;

import java.util.List;

@Tag(name = "Ban API", description = "Операции для модераторов")
@RequestMapping("/banned")
public interface BanController {

    @PostMapping("/delete")
    @Operation(summary = "Удаление пользователя из забаненных",
            description = "Почта пользователя выносится из черного списка.")
    ResponseEntity<Void> unbanUser(
            @RequestHeader int moderatorId,
            @RequestParam String email);

    @GetMapping("/")
    @Operation(summary = "Получить список заблокированных почт",
            description = "Все данные о заблокированных пользовательских email адресах")
    ResponseEntity<List<BannedEmailResponse>> getListOfBanned();
}
