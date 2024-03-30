package ru.orobtsovv.authservice.exception.account;

import static ru.orobtsovv.authservice.utils.Constants.ACCOUNT_NOT_FOUND;

public class AccountNotFoundException extends AccountException {
    public AccountNotFoundException() {
        super(ACCOUNT_NOT_FOUND);
    }
}
