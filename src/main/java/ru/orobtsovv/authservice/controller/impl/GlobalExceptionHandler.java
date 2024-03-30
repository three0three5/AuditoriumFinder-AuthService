package ru.orobtsovv.authservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.orobtsovv.authservice.dto.CommonDTO;
import ru.orobtsovv.authservice.exception.SimpleExceptionMessagesCreator;
import ru.orobtsovv.authservice.exception.account.AccountException;
import ru.orobtsovv.authservice.exception.account.RefreshExpiredException;
import ru.orobtsovv.authservice.exception.email.EmailException;

import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final SimpleExceptionMessagesCreator exceptionMessagesCreator;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return exceptionMessagesCreator.getExceptionMessages(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailException.class)
    public CommonDTO handleEmailExceptions(EmailException e) {
        return new CommonDTO(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(RefreshExpiredException.class)
    public CommonDTO handleRefreshException(RefreshExpiredException e) {
        return new CommonDTO(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountException.class)
    public CommonDTO handleAccountExceptions(AccountException e) {
        return new CommonDTO(e.getMessage());
    }
}
