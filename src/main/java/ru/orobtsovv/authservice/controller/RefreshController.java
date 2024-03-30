package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.orobtsovv.authservice.dto.TokenResponse;

@Tag(name = "Refresh API")
@RequestMapping("/refresh")
public interface RefreshController {
    @Operation(summary = "Получение новой пары токенов",
            description = "Если refresh производится по уже использованному токену, производится " +
                    "полный logout")
    @PostMapping
    ResponseEntity<TokenResponse> refreshToken(@RequestParam String token);
}
