package ru.hse.authservice.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.authservice.controller.LogoutController;

@RestController
public class LogoutControllerImpl implements LogoutController {
    @Override
    public ResponseEntity<Void> logoutAll(String jwt) {
        return null;
    }

    @Override
    public ResponseEntity<Void> logout(String jwt) {
        return null;
    }
}
