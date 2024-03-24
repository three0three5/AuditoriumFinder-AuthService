package ru.hse.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.authservice.controller.CertsController;
import ru.hse.authservice.dto.RsaPublicKeyResponse;
import ru.hse.authservice.service.impl.KeyService;

@RestController
@RequiredArgsConstructor
public class CertsControllerImpl implements CertsController {
    private final KeyService keyService;

    @Override
    public ResponseEntity<RsaPublicKeyResponse> getPublicSignatureKey() {
        return ResponseEntity.ok(keyService.getPublicSignatureKey());
    }
}
