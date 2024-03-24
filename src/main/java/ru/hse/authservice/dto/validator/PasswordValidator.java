package ru.hse.authservice.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String>  {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        int length = value.length();
        return length >= 8 && length <= 128 && passwordRegex(value);
    }

    private boolean passwordRegex(String password) {
        return true; // TODO
    }
}
