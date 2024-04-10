package ru.orobtsovv.authservice.exception.account;

import static ru.orobtsovv.authservice.utils.Constants.WRONG_ROLE;

public class WrongRoleException extends AccountException {
    public WrongRoleException() {
        super(WRONG_ROLE);
    }
}
