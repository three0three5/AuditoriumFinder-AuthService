package ru.orobtsovv.authservice.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.orobtsovv.authservice.service.impl.BlacklistService;

import java.util.regex.Pattern;

import static ru.orobtsovv.authservice.utils.Constants.EMAIL_REGEX;

@Component
@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private final BlacklistService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() <= 32 && emailRegex(value) && service.check(value);
    }

    private boolean emailRegex(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
}
