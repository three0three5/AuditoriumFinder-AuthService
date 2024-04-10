package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.domain.repository.StudentRepository;
import ru.orobtsovv.authservice.dto.CommonDTO;
import ru.orobtsovv.authservice.dto.request.PasswordChangeRequest;

import static ru.orobtsovv.authservice.utils.Constants.PASSWORD_UPDATED_RESPONSE;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordService {
    private final EmailCodeService codeService;
    private final StudentRepository repository;
    private final PasswordEncoder encoder;

    public CommonDTO setPassword(PasswordChangeRequest request) {
        codeService.verifyEmail(request.getEmail(), request.getEmailCode());
        int affected = repository.updatePassword(request.getEmail(), encoder.encode(request.getNewPassword()));
        log.info("changing password affected: %d".formatted(affected));
        return new CommonDTO(PASSWORD_UPDATED_RESPONSE);
    }
}
