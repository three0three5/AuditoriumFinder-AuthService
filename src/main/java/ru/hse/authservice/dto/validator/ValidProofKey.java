package ru.hse.authservice.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.hse.authservice.utils.Constants.PROOF_KEY_PROVIDED;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProofKeyValidator.class)
public @interface ValidProofKey {
    String message() default PROOF_KEY_PROVIDED;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}