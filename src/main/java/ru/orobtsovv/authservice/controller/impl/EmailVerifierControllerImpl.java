package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.EmailVerifierController;
import ru.orobtsovv.authservice.dto.CommonDTO;
import ru.orobtsovv.authservice.dto.request.EmailRequest;
import ru.orobtsovv.authservice.service.impl.EmailCodeService;

@RestController
@RequiredArgsConstructor
public class EmailVerifierControllerImpl implements EmailVerifierController {
    private final EmailCodeService emailCodeService;

    @Override
    public ResponseEntity<CommonDTO> sendConfirmationCode(EmailRequest email) {
        return ResponseEntity.ok(emailCodeService.sendCode(email));
    }
}
