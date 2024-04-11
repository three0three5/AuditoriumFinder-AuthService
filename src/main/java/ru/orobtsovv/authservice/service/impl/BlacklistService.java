package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.domain.entity.BannedEmailEntity;
import ru.orobtsovv.authservice.domain.repository.BlacklistRepository;
import ru.orobtsovv.authservice.exception.account.BannedException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlacklistService {
    private final BlacklistRepository repository;

    public boolean check(String email) {
        log.info("blacklist check");
        Optional<BannedEmailEntity> optional = repository.findBlacklisted(email);
        if (optional.isEmpty()) return true;
        log.info("throwing banned exception for %s".formatted(email));
        throw new BannedException(optional.get().getReason(), optional.get().getBannedUntil());
    }
}
