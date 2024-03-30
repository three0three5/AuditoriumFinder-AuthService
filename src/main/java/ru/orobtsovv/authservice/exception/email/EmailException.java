package ru.orobtsovv.authservice.exception.email;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailException extends RuntimeException {
    public EmailException(String message) {
        super(message);
    }
}
