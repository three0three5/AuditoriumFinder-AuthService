package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.authservice.client.AsyncUserServiceClient;
import ru.orobtsovv.authservice.domain.entity.AccountEntity;
import ru.orobtsovv.authservice.domain.entity.RefreshTokenEntity;
import ru.orobtsovv.authservice.domain.repository.RefreshRepository;
import ru.orobtsovv.authservice.dto.response.TokenResponse;
import ru.orobtsovv.authservice.exception.account.RefreshExpiredException;
import ru.orobtsovv.authservice.utils.AuthProperties;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {
    private final AuthProperties authProperties;
    private final RefreshRepository refreshRepository;
    private final JwtService jwtService;
    private final AsyncUserServiceClient userServiceClient;

    @Transactional
    public TokenResponse newSession(AccountEntity accountEntity, boolean isTelegram) {
        if (isTelegram) {
            refreshRepository.deleteActiveTelegramSession(accountEntity.getUserId());
        }
        RefreshTokenEntity tokenEntity = new RefreshTokenEntity()
                .setRefreshValue(RandomStringGenerator.generateRandomString(authProperties.getRefreshLength()))
                .setUniqueSession(isTelegram)
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

    @Transactional(noRollbackFor = {RefreshExpiredException.class})
    public TokenResponse refresh(String token) {
        RefreshTokenEntity tokenEntity = refreshRepository.findById(token)
                .orElseThrow(RefreshExpiredException::new);
        log.info("refreshing for " + tokenEntity.getAccountEntity().getUserId());
        if (tokenEntity.getValidUntil().isBefore(LocalDateTime.now()))
            throw new RefreshExpiredException();
        if (tokenEntity.getUsedAt() != null) {
            removeAllActiveSessions(tokenEntity.getAccountEntity().getUserId());
            throw new RefreshExpiredException();
        }
        tokenEntity.setUsedAt(LocalDateTime.now());
        refreshRepository.save(tokenEntity);
        return newSession(tokenEntity.getAccountEntity(), tokenEntity.isUniqueSession());
    }

    public void removeAllActiveSessions(int userid) {
        int affected = refreshRepository.removeAllActive(userid);
        log.info("Logout all for %d; affected %d sessions".formatted(userid, affected));
    }

    public void removeAllActiveSessions(String refreshToken) {
        Optional<RefreshTokenEntity> optional = refreshRepository.findById(refreshToken);
        if (optional.isEmpty()) return;
        removeAllActiveSessions(optional.get().getAccountEntity().getUserId());
    }

    @Transactional
    public void removeActiveSession(String refreshToken) {
        Optional<RefreshTokenEntity> optional = refreshRepository.findById(refreshToken);
        if (optional.isEmpty()) return;
        RefreshTokenEntity entity = optional.get();
        if (entity.getUsedAt() != null) {
            removeAllActiveSessions(entity.getAccountEntity().getUserId());
            return;
        }
        if (entity.isUniqueSession()) {
            userServiceClient.removeTgHandle(entity.getAccountEntity().getUserId());
        }
        refreshRepository.delete(entity);
    }
}
