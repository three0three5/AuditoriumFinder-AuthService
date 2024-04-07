package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.authservice.client.AsyncUserServiceClient;
import ru.orobtsovv.authservice.domain.entity.AccountEntity;
import ru.orobtsovv.authservice.domain.entity.AccountRole;
import ru.orobtsovv.authservice.domain.repository.AccountRepository;
import ru.orobtsovv.authservice.dto.ProfileCreateRequest;
import ru.orobtsovv.authservice.dto.SignUpRequest;
import ru.orobtsovv.authservice.dto.SignUpTelegramRequest;
import ru.orobtsovv.authservice.dto.TokenResponse;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final SessionService sessionService;
    private final AccountRepository accountRepository;
    private final AsyncUserServiceClient userServiceClient;
    private final PasswordEncoder encoder;

    @Transactional
    public TokenResponse createNewTgAccount(SignUpTelegramRequest request) {
        AccountEntity toSave = new AccountEntity()
                .setEmail(request.getEmail())
                .setTelegramHandle(request.getTelegramHandle())
                .setAccountRole(AccountRole.ROLE_USER);
        toSave = accountRepository.save(toSave);
        TokenResponse result = sessionService.newSession(toSave, true);
        userServiceClient.createNewProfile(new ProfileCreateRequest()
                .setEmail(request.getEmail())
                .setId(toSave.getUserId())
                .setNickname(request.getNickname())
                .setTelegramHandle(request.getTelegramHandle()));
        return result;
    }

    @Transactional
    public TokenResponse createNewAccount(SignUpRequest request) {
        AccountEntity toSave = new AccountEntity()
                .setEmail(request.getEmail())
                .setHashedPassword(encoder.encode(request.getPassword()))
                .setAccountRole(AccountRole.ROLE_USER);
        toSave = accountRepository.save(toSave);
        TokenResponse result = sessionService.newSession(toSave, false);
        userServiceClient.createNewProfile(new ProfileCreateRequest()
                .setEmail(request.getEmail())
                .setId(toSave.getUserId())
                .setNickname(request.getNickname()));
        return result;
    }

    @Transactional
    public TokenResponse newSessionWithTelegram(AccountEntity accountEntity, String telegramHandle) {
        TokenResponse result = sessionService.newSession(accountEntity, true);
        if (!accountEntity.getTelegramHandle().equals(telegramHandle)) {
            accountEntity.setTelegramHandle(telegramHandle);
            accountRepository.save(accountEntity);
            userServiceClient.propagateHandle(accountEntity.getUserId(), telegramHandle);
        }
        return result;
    }
}
