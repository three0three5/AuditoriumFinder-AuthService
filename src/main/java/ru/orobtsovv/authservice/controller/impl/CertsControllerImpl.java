package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.CertsController;
import ru.orobtsovv.authservice.dto.response.RsaPublicKeyResponse;
import ru.orobtsovv.authservice.service.impl.KeyService;

@RestController
@RequiredArgsConstructor
public class CertsControllerImpl implements CertsController {
    private final KeyService keyService;

    @Override
    public ResponseEntity<RsaPublicKeyResponse> getPublicSignatureKey() {
        return ResponseEntity.ok(keyService.getPublicSignatureKey());
    }
}
