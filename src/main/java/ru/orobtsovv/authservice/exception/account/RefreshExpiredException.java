package ru.orobtsovv.authservice.exception.account;

import static ru.orobtsovv.authservice.utils.Constants.REFRESH_EXPIRED;

public class RefreshExpiredException extends AccountException {
    public RefreshExpiredException() {
        super(REFRESH_EXPIRED);
    }
}
