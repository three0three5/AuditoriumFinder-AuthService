package ru.orobtsovv.authservice.exception.email;

import static ru.orobtsovv.authservice.utils.Constants.EMAIL_CODE_EXPIRED;

public class EmailCodeExpiredException extends EmailException {
    public EmailCodeExpiredException() {
        super(EMAIL_CODE_EXPIRED);
    }
}
