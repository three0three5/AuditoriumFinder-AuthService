package ru.orobtsovv.authservice.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.BanController;

@RestController
public class BanControllerImpl implements BanController {
    @Override
    public ResponseEntity<Void> banUser(int userid) {
        return null;
    }

    @Override
    public ResponseEntity<Void> unbanUser(String email) {
        return null;
    }
}
