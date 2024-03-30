package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.orobtsovv.authservice.dto.RsaPublicKeyResponse;

@Tag(name = "Certs API")
@RequestMapping("/certs")
public interface CertsController {
    @Operation(summary = "Публичный ключ подписи jwt")
    @GetMapping
    ResponseEntity<RsaPublicKeyResponse> getPublicSignatureKey();
}
