package ru.orobtsovv.authservice.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.client.EmailSenderClient;

@Service
@Slf4j
@Profile("with-mocks")
public class EmailSenderMock implements EmailSenderClient {
    @Override
    public void send(String email, String code) {
        log.info("Sending code %s to %s".formatted(code, email));
    }
}
