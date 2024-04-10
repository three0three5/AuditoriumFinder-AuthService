package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.authservice.client.AsyncUserServiceClient;
import ru.orobtsovv.authservice.domain.entity.AccountRole;
import ru.orobtsovv.authservice.domain.entity.StudentEntity;
import ru.orobtsovv.authservice.domain.repository.StudentRepository;
import ru.orobtsovv.authservice.dto.request.ProfileCreateRequest;
import ru.orobtsovv.authservice.dto.request.SignUpRequest;
import ru.orobtsovv.authservice.dto.request.SignUpTelegramRequest;
import ru.orobtsovv.authservice.dto.response.TokenResponse;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final SessionService sessionService;
    private final StudentRepository studentRepository;
    private final AsyncUserServiceClient userServiceClient;
    private final PasswordEncoder encoder;

    @Transactional
    public TokenResponse createNewTgAccount(SignUpTelegramRequest request) {
        StudentEntity student = new StudentEntity()
                .setTelegramHandle(request.getTelegramHandle())
                .setEmail(request.getEmail());
        student.setAccountRole(AccountRole.ROLE_USER);
        student = studentRepository.save(student);
        TokenResponse result = sessionService.newSession(student, true);
        userServiceClient.createNewProfile(new ProfileCreateRequest()
                .setEmail(request.getEmail())
                .setId(student.getUserId())
                .setNickname(request.getNickname())
                .setTelegramHandle(request.getTelegramHandle()));
        return result;
    }

    @Transactional
    public TokenResponse createNewAccount(SignUpRequest request) {
        StudentEntity student = new StudentEntity()
                .setEmail(request.getEmail())
                .setHashedPassword(encoder.encode(request.getPassword()));
        student.setAccountRole(AccountRole.ROLE_USER);
        student = studentRepository.save(student);
        TokenResponse result = sessionService.newSession(student, false);
        userServiceClient.createNewProfile(new ProfileCreateRequest()
                .setEmail(request.getEmail())
                .setId(student.getUserId())
                .setNickname(request.getNickname()));
        return result;
    }

    @Transactional
    public TokenResponse newSessionWithTelegram(StudentEntity entity, String telegramHandle) {
        TokenResponse result = sessionService.newSession(entity, true);
        if (!entity.getTelegramHandle().equals(telegramHandle)) {
            entity.setTelegramHandle(telegramHandle);
            studentRepository.save(entity);
            userServiceClient.propagateHandle(entity.getUserId(), telegramHandle);
        }
        return result;
    }
}
