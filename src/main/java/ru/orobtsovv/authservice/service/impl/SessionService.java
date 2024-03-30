package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.authservice.domain.entity.AccountEntity;
import ru.orobtsovv.authservice.domain.entity.RefreshTokenEntity;
import ru.orobtsovv.authservice.domain.repository.RefreshRepository;
import ru.orobtsovv.authservice.dto.TokenResponse;
import ru.orobtsovv.authservice.utils.AuthProperties;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final AuthProperties authProperties;
    private final RefreshRepository refreshRepository;
    private final JwtService jwtService;

    @Transactional
    public TokenResponse newSession(AccountEntity accountEntity, boolean isTelegram) {
        if (isTelegram) {
            refreshRepository.deleteTelegramSession(accountEntity.getUserId());
        }
        RefreshTokenEntity tokenEntity = new RefreshTokenEntity()
                .setRefreshValue(RandomStringGenerator.generateRandomString(authProperties.getRefreshLength()))
                .setTelegramSession(isTelegram)
                .setAccountEntity(accountEntity)
                .setValidUntil(LocalDateTime.now().plusDays(authProperties.getRefreshLifespan()));
        tokenEntity = refreshRepository.save(tokenEntity);

        String jwt = jwtService.createJwt(accountEntity.getUserId(),
                List.of(accountEntity.getAccountRole().name()));

        return new TokenResponse()
                .setUserid(accountEntity.getUserId())
                .setRefresh(tokenEntity.getRefreshValue())
                .setJwt(jwt);
    }
}
