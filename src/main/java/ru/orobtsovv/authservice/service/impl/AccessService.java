package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.domain.entity.AccountRole;
import ru.orobtsovv.authservice.domain.entity.ExternalEntity;
import ru.orobtsovv.authservice.domain.repository.ExternalEntityRepository;
import ru.orobtsovv.authservice.dto.request.ClientCredentialsRequest;
import ru.orobtsovv.authservice.dto.response.JwtTokenResponse;
import ru.orobtsovv.authservice.dto.response.TokenResponse;
import ru.orobtsovv.authservice.exception.account.AccountNotFoundException;
import ru.orobtsovv.authservice.exception.account.WrongPasswordException;
import ru.orobtsovv.authservice.exception.account.WrongRoleException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessService {
    private final ExternalEntityRepository repository;
    private final SessionService sessionService;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public TokenResponse moderator(ClientCredentialsRequest request) {
        ExternalEntity entity = repository.findByClientId(request.getClientId())
                .orElseThrow(AccountNotFoundException::new);
        AccountRole role = entity.getAccountRole();
        if (!AccountRole.ROLE_MODERATOR.equals(role)) throw new WrongRoleException();
        return sessionService.newSession(entity, false);
    }

    public JwtTokenResponse telegram(ClientCredentialsRequest request) {
        ExternalEntity entity = repository.findByClientId(request.getClientId())
                .orElseThrow(AccountNotFoundException::new);
        AccountRole role = entity.getAccountRole();
        if (!AccountRole.ROLE_TG_SERVICE.equals(role)) throw new WrongRoleException();
        if (!encoder.matches(request.getClientSecret(), entity.getClientSecret()))
            throw new WrongPasswordException();
        return new JwtTokenResponse()
                .setJwt(jwtService.createJwt(entity.getUserId(), List.of(role.name())));
    }
}
