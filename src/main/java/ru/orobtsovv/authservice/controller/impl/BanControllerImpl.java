package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.orobtsovv.authservice.controller.BanController;
import ru.orobtsovv.authservice.dto.response.BannedEmailResponse;
import ru.orobtsovv.authservice.service.impl.BanService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BanControllerImpl implements BanController {
    private final BanService banService;

    @Override
    public ResponseEntity<Void> unbanUser(int moderatorId, String email) {
        banService.unbanUser(email, moderatorId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<BannedEmailResponse>> getListOfBanned() {
        return ResponseEntity.ok(banService.getAll());
    }
}
