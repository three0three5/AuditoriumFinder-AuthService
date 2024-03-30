package ru.orobtsovv.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.client.UserServiceClient;

@Service
@RequiredArgsConstructor
public class TelegramService {
    private final UserServiceClient client;
    public void propagateHandle(int userid, String telegramHandle) {
        client.propagateHandle(userid, telegramHandle);
    }
}
