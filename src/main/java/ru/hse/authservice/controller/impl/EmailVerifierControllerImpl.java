package ru.hse.authservice.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.authservice.controller.EmailVerifierController;
import ru.hse.authservice.dto.CommonDTO;
import ru.hse.authservice.dto.EmailRequest;
import ru.hse.authservice.dto.EmailWithCodeRequest;
import ru.hse.authservice.dto.ProofKeyResponse;

@RestController
public class EmailVerifierControllerImpl implements EmailVerifierController {
    @Override
    public ResponseEntity<CommonDTO> sendConfirmationCode(EmailRequest email) {
        return null;
    }

    @Override
    public ResponseEntity<ProofKeyResponse> verifyEmail(EmailWithCodeRequest request) {
        return null;
    }
}
