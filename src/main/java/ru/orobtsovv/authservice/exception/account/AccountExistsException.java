package ru.orobtsovv.authservice.exception.account;

import static ru.orobtsovv.authservice.utils.Constants.ACCOUNT_EXISTS_CONFLICT;

public class AccountExistsException extends AccountException {
    public AccountExistsException() {
        super(ACCOUNT_EXISTS_CONFLICT);
    }
}
