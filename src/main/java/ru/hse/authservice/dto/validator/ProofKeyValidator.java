package ru.hse.authservice.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static ru.hse.authservice.utils.Constants.PROOF_KEY_LENGTH;

public class ProofKeyValidator implements ConstraintValidator<ValidProofKey, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() == PROOF_KEY_LENGTH;
    }
}
