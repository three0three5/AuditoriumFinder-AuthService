package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.orobtsovv.authservice.dto.CommonDTO;
import ru.orobtsovv.authservice.dto.request.EmailRequest;

@Tag(name = "Email Verifier API",
        description = "Эндпоинты для операций с электронной почтой")
@RequestMapping("/email")
public interface EmailVerifierController {
    @Operation(summary = "Запрос на отправку кода подтверждения")
    @PostMapping("/confirm")
    ResponseEntity<CommonDTO> sendConfirmationCode(@Valid @RequestBody EmailRequest email);
}
