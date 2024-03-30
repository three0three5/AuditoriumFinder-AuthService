package ru.orobtsovv.authservice.exception.account;

import static ru.orobtsovv.authservice.utils.Constants.WRONG_PASSWORD;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super(WRONG_PASSWORD);
    }
}
