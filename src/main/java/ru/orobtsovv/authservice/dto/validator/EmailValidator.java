package ru.orobtsovv.authservice.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static ru.orobtsovv.authservice.utils.Constants.EMAIL_REGEX;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() <= 32 && emailRegex(value);
    }

    private boolean emailRegex(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
}
