package ru.hse.authservice.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() <= 32 && emailRegex(value);
    }

    private boolean emailRegex(String email) {
        return true; // TODO
    }
}
