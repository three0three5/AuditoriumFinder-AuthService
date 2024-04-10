package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.domain.entity.StudentEntity;
import ru.orobtsovv.authservice.domain.repository.StudentRepository;
import ru.orobtsovv.authservice.dto.request.SignInRequest;
import ru.orobtsovv.authservice.dto.request.SignInTelegramRequest;
import ru.orobtsovv.authservice.dto.request.SignUpRequest;
import ru.orobtsovv.authservice.dto.request.SignUpTelegramRequest;
import ru.orobtsovv.authservice.dto.response.TokenResponse;
import ru.orobtsovv.authservice.exception.account.AccountExistsException;
import ru.orobtsovv.authservice.exception.account.AccountNotFoundException;
import ru.orobtsovv.authservice.exception.account.WrongPasswordException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignService {
    private final EmailCodeService codeService;
    private final StudentRepository studentRepository;
    private final AccountService accountService;
    private final PasswordEncoder encoder;
    private final SessionService sessionService;

    public TokenResponse signUp(SignUpTelegramRequest request) {
        codeService.verifyEmail(request.getEmail(), request.getEmailCode());
        Optional<StudentEntity> optionalAccount = studentRepository.findByEmail(request.getEmail());
        if (optionalAccount.isPresent()) {
            return accountService.newSessionWithTelegram(optionalAccount.get(), request.getTelegramHandle());
        }
        return accountService.createNewTgAccount(request);
    }

    public TokenResponse signUp(SignUpRequest request) {
        codeService.verifyEmail(request.getEmail(), request.getEmailCode());
        Optional<StudentEntity> optionalAccount = studentRepository.findByEmail(request.getEmail());
        if (optionalAccount.isPresent()) {
            throw new AccountExistsException();
        }
        return accountService.createNewAccount(request);
    }

    public TokenResponse signIn(SignInRequest request) {
        StudentEntity account = studentRepository
                .findByEmail(request.getEmail())
                .orElseThrow(AccountNotFoundException::new);
        if (!encoder.matches(request.getPassword(), account.getHashedPassword()))
            throw new WrongPasswordException();
        return sessionService.newSession(account, false);
    }

    public TokenResponse signInTg(SignInTelegramRequest request) {
        codeService.verifyEmail(request.getEmail(), request.getEmailCode());
        StudentEntity account = studentRepository
                .findByEmail(request.getEmail())
                .orElseThrow(AccountNotFoundException::new);
        return accountService.newSessionWithTelegram(account, request.getTelegramHandle());
    }
}
