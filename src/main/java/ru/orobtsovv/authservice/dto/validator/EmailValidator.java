package ru.orobtsovv.authservice.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.orobtsovv.authservice.domain.entity.BannedEmailEntity;
import ru.orobtsovv.authservice.domain.repository.BlacklistRepository;
import ru.orobtsovv.authservice.exception.account.BannedException;

import java.util.Optional;
import java.util.regex.Pattern;

import static ru.orobtsovv.authservice.utils.Constants.EMAIL_REGEX;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private final BlacklistRepository blacklistRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() <= 32 && emailRegex(value) && blacklistedCheck(value);
    }

    private boolean emailRegex(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    private boolean blacklistedCheck(String email) {
        Optional<BannedEmailEntity> optional = blacklistRepository.findValidByEmail(email);
        if (optional.isPresent())
            throw new BannedException(optional.get().getReason(), optional.get().getBannedUntil());
        return true;
    }
}
