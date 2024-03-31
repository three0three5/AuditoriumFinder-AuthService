package ru.orobtsovv.authservice.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class DateValidator implements ConstraintValidator<ValidDate, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.isAfter(LocalDateTime.now()) &&
                value.isBefore(LocalDateTime.now().plusYears(4));
    }
}
