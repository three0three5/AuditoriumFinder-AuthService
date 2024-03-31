package ru.orobtsovv.authservice.dto.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.orobtsovv.authservice.service.impl.BlacklistService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailValidatorTest {
    @InjectMocks
    private EmailValidator validator;
    @Mock
    private BlacklistService service;

    @BeforeEach
    void init() {
        when(service.check(any(String.class))).thenReturn(true);
    }

    @Test
    void givenCorrectEmail_whenValidate_thenTrue() {
        boolean result = validator.isValid("ololoev@edu.hse.ru", null);
        assertTrue(result);
    }
}