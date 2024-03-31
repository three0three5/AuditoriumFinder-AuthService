package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.domain.repository.AccountRepository;
import ru.orobtsovv.authservice.dto.CommonDTO;
import ru.orobtsovv.authservice.dto.PasswordChangeRequest;

import static ru.orobtsovv.authservice.utils.Constants.PASSWORD_UPDATED_RESPONSE;

@Service
@RequiredArgsConstructor
public class PasswordService {
    private final EmailCodeService codeService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;

    public CommonDTO setPassword(PasswordChangeRequest request) {
        codeService.verifyEmail(request.getEmail(), request.getEmailCode());
        accountRepository.updatePassword(request.getEmail(), encoder.encode(request.getNewPassword()));
        return new CommonDTO(PASSWORD_UPDATED_RESPONSE);
    }
}
