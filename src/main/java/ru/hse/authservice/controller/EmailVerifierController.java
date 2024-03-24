package ru.hse.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hse.authservice.dto.CommonDTO;
import ru.hse.authservice.dto.EmailRequest;
import ru.hse.authservice.dto.EmailWithCodeRequest;
import ru.hse.authservice.dto.ProofKeyResponse;

@Tag(name = "Email Verifier API",
        description = "Эндпоинты для операций с электронной почтой")
@RequestMapping("/email")
public interface EmailVerifierController {
    @Operation(summary = "Запрос на отправку кода подтверждения")
    @PostMapping("/confirm")
    ResponseEntity<CommonDTO> sendConfirmationCode(@Valid @RequestBody EmailRequest email);

    @Operation(summary = "Подтверждение почты с помощью кода",
            description = "Пользователь отправляет код, который получил по почте. " +
                    "В ответ возвращается proof key, который используется для последующей " +
                    "регистрации и/или входа, смены пароля. Клиент должен сохранить его " +
                    "и направлять на соответствующие эндпоинты")
    @PostMapping("/verify")
    ResponseEntity<ProofKeyResponse> verifyEmail(@Valid @RequestBody EmailWithCodeRequest request);
}
