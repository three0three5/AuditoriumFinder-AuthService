package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.domain.entity.BannedEmailEntity;
import ru.orobtsovv.authservice.domain.repository.BlacklistRepository;
import ru.orobtsovv.authservice.exception.account.BannedException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlacklistService {
    private final BlacklistRepository repository;

    public boolean check(String email) {
        Optional<BannedEmailEntity> optional = repository.findValidByEmail(email);
        if (optional.isEmpty()) return true;
        throw new BannedException(optional.get().getReason(), optional.get().getBannedUntil());
    }
}
