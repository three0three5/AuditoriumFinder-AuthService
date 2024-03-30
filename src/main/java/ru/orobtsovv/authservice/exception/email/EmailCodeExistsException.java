package ru.orobtsovv.authservice.exception.email;

import static ru.orobtsovv.authservice.utils.Constants.EMAIL_CODE_EXISTS_MESSAGE;

public class EmailCodeExistsException extends EmailException {
    public EmailCodeExistsException() {
        super(EMAIL_CODE_EXISTS_MESSAGE);
    }
}
