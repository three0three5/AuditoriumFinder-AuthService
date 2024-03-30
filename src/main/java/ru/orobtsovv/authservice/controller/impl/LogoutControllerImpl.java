package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.LogoutController;
import ru.orobtsovv.authservice.service.impl.SessionService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LogoutControllerImpl implements LogoutController {
    private final SessionService sessionService;

    @Override
    public ResponseEntity<Void> logoutAll(String refreshToken) {
        sessionService.removeAllActiveSessions(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> logout(String refreshToken) {
        sessionService.removeActiveSession(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
