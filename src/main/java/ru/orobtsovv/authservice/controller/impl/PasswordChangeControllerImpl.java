package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.PasswordChangeController;
import ru.orobtsovv.authservice.dto.CommonDTO;
import ru.orobtsovv.authservice.dto.request.PasswordChangeRequest;
import ru.orobtsovv.authservice.service.impl.PasswordService;

@RestController
@RequiredArgsConstructor
public class PasswordChangeControllerImpl implements PasswordChangeController {
    private final PasswordService service;

    @Override
    public ResponseEntity<CommonDTO> passwordChange(PasswordChangeRequest request) {
        return ResponseEntity.ok(service.setPassword(request));
    }
}
