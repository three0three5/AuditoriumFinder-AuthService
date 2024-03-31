package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.authservice.domain.entity.AccountEntity;
import ru.orobtsovv.authservice.domain.entity.BannedEmailEntity;
import ru.orobtsovv.authservice.domain.repository.AccountRepository;
import ru.orobtsovv.authservice.domain.repository.BlacklistRepository;
import ru.orobtsovv.authservice.domain.repository.RefreshRepository;
import ru.orobtsovv.authservice.dto.BanRequest;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BanService {
    private final RefreshRepository refreshRepository;
    private final BlacklistRepository blacklistRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void banUser(BanRequest request) {
        Optional<AccountEntity> optional = accountRepository.findById(request.getUserid());
        if (optional.isEmpty()) return;
        refreshRepository.deleteAllByAccountEntity(optional.get());
        accountRepository.deleteById(request.getUserid());
        BannedEmailEntity entity = new BannedEmailEntity();
        entity.setEmail(optional.get().getEmail());
        entity.setBannedUntil(request.getBannedUntil());
        entity.setReason(request.getReason());
        entity.setModeratorId(request.getModeratorId());
        blacklistRepository.save(entity);
    }

    @Transactional
    public void unbanUser(String email) {
        blacklistRepository.deleteById(email);
    }
}
