package ru.orobtsovv.authservice.exception.email;

import static ru.orobtsovv.authservice.utils.Constants.EMAIL_CODE_NOT_VALID;

public class EmailCodeNotValidException extends EmailException {
    public EmailCodeNotValidException() {
        super(EMAIL_CODE_NOT_VALID);
    }
}
