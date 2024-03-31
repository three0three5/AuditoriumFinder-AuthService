package ru.orobtsovv.authservice.exception.account;

import lombok.Getter;

import java.time.LocalDateTime;

import static ru.orobtsovv.authservice.utils.Constants.BANNED_MESSAGE;

@Getter
public class BannedException extends AccountException {
    private final String reason;
    private final LocalDateTime bannedUntil;
    public BannedException(String reason, LocalDateTime bannedUntil) {
        super(BANNED_MESSAGE);
        this.reason = reason;
        this.bannedUntil = bannedUntil;
    }
}
