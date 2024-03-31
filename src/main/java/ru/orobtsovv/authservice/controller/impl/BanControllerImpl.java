package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.BanController;
import ru.orobtsovv.authservice.dto.BanRequest;
import ru.orobtsovv.authservice.service.impl.BanService;

@RestController
@RequiredArgsConstructor
public class BanControllerImpl implements BanController {
    private final BanService banService;

    @Override
    public ResponseEntity<Void> banUser(BanRequest request) {
        banService.banUser(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> unbanUser(String email) {
        banService.unbanUser(email);
        return ResponseEntity.ok().build();
    }
}
