package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.client.EmailSenderClient;
import ru.orobtsovv.authservice.domain.entity.EmailCodeEntity;
import ru.orobtsovv.authservice.domain.redis.EmailCodeRepository;
import ru.orobtsovv.authservice.dto.CommonDTO;
import ru.orobtsovv.authservice.dto.request.EmailRequest;
import ru.orobtsovv.authservice.exception.email.EmailCodeExistsException;
import ru.orobtsovv.authservice.exception.email.EmailCodeExpiredException;
import ru.orobtsovv.authservice.exception.email.EmailCodeNotValidException;
import ru.orobtsovv.authservice.utils.EmailProperties;

import static ru.orobtsovv.authservice.utils.Constants.EMAIL_CODE_SENT;

@Service
@RequiredArgsConstructor
public class EmailCodeService {
    private final EmailSenderClient senderClient;
    private final EmailCodeRepository codeRepository;
    private final EmailProperties properties;

    public CommonDTO sendCode(EmailRequest email) {
        if (codeRepository.existsById(email.getEmail())) throw new EmailCodeExistsException();
        EmailCodeEntity code = new EmailCodeEntity();
        code.setExpiration(properties.getCodeLifespan());
        code.setEmail(email.getEmail());
        code.setCode(RandomStringGenerator.generateRandomString(properties.getCodeLength()));
        senderClient.send(code.getEmail(), code.getCode());
        codeRepository.save(code);
        return new CommonDTO(EMAIL_CODE_SENT);
    }

    public void verifyEmail(String email, String code) {
        EmailCodeEntity codeEntity = codeRepository
                .findById(email)
                .orElseThrow(EmailCodeExpiredException::new);
        if (!codeEntity.getCode().equals(code)) throw new EmailCodeNotValidException();
        codeRepository.delete(codeEntity);
    }
}
