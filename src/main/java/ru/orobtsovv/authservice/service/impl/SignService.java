package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.domain.entity.AccountEntity;
import ru.orobtsovv.authservice.domain.repository.AccountRepository;
import ru.orobtsovv.authservice.dto.SignInRequest;
import ru.orobtsovv.authservice.dto.SignInTelegramRequest;
import ru.orobtsovv.authservice.dto.SignUpRequest;
import ru.orobtsovv.authservice.dto.SignUpTelegramRequest;
import ru.orobtsovv.authservice.dto.TokenResponse;
import ru.orobtsovv.authservice.exception.account.AccountExistsException;
import ru.orobtsovv.authservice.exception.account.AccountNotFoundException;
import ru.orobtsovv.authservice.exception.account.WrongPasswordException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignService {
    private final EmailCodeService codeService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final PasswordEncoder encoder;
    private final SessionService sessionService;

    public TokenResponse signUp(SignUpTelegramRequest request) {
        codeService.verifyEmail(request.getEmail(), request.getEmailCode());
        Optional<AccountEntity> optionalAccount = accountRepository.findByEmail(request.getEmail());
        if (optionalAccount.isPresent()) {
            return accountService.newSessionWithTelegram(optionalAccount.get(), request.getTelegramHandle());
        }
        return accountService.createNewTgAccount(request);
    }

    public TokenResponse signUp(SignUpRequest request) {
        codeService.verifyEmail(request.getEmail(), request.getEmailCode());
        Optional<AccountEntity> optionalAccount = accountRepository.findByEmail(request.getEmail());
        if (optionalAccount.isPresent()) {
            throw new AccountExistsException();
        }
        return accountService.createNewAccount(request);
    }

    public TokenResponse signIn(SignInRequest request) {
        AccountEntity account = accountRepository
                .findByEmail(request.getEmail())
                .orElseThrow(AccountNotFoundException::new);
        if (!encoder.matches(request.getPassword(), account.getHashedPassword()))
            throw new WrongPasswordException();
        return sessionService.newSession(account, false);
    }

    public TokenResponse signInTg(SignInTelegramRequest request) {
        codeService.verifyEmail(request.getEmail(), request.getEmailCode());
        AccountEntity account = accountRepository
                .findByEmail(request.getEmail())
                .orElseThrow(AccountNotFoundException::new);
        return accountService.newSessionWithTelegram(account, request.getTelegramHandle());
    }
}
