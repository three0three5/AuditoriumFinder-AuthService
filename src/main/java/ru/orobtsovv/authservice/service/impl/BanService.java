package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.authservice.domain.entity.AccountEntity;
import ru.orobtsovv.authservice.domain.entity.BannedEmailEntity;
import ru.orobtsovv.authservice.domain.repository.AccountRepository;
import ru.orobtsovv.authservice.domain.repository.BlacklistRepository;
import ru.orobtsovv.authservice.domain.repository.RefreshRepository;
import ru.orobtsovv.authservice.dto.response.BannedEmailResponse;
import ru.orobtsovv.authservice.dto.message.ProfileDeleteMessage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BanService {
    private final RefreshRepository refreshRepository;
    private final BlacklistRepository blacklistRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void banUser(ProfileDeleteMessage event) {
        Optional<AccountEntity> optional = accountRepository.findById(event.getUserid());
        if (optional.isEmpty()) return;
        int affected = refreshRepository.deleteAllByAccountEntity(optional.get());
        log.info("deleted refresh: %d".formatted(affected));
        accountRepository.deleteById(event.getUserid());
        BannedEmailEntity entity = new BannedEmailEntity();
        entity.setEmail(optional.get().getEmail());
        entity.setBannedUntil(event.getBannedUntil());
        entity.setReason(event.getReason());
        entity.setModeratorId(event.getModeratorId());
        blacklistRepository.save(entity);
    }

    @Transactional
    public void unbanUser(String email, int moderatorId) {
        log.info("Whitelisted %s by moderator %d".formatted(email, moderatorId));
        blacklistRepository.deleteById(email);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<BannedEmailResponse> getAll() {
        return blacklistRepository.findAll().stream()
                .map(entity -> new BannedEmailResponse()
                        .setEmail(entity.getEmail())
                        .setReason(entity.getReason())
                        .setBannedUntil(entity.getBannedUntil())
                        .setModeratorId(entity.getModeratorId()))
                .collect(Collectors.toList());
    }
}
