package ru.orobtsovv.authservice.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.client.UserServiceClient;
import ru.orobtsovv.authservice.dto.ProfileCreateRequest;

@Service
@Slf4j
@Profile("with-mocks")
public class UserServiceMock implements UserServiceClient {
    @Override
    public void propagateHandle(int userid, String telegramHandle) {
        log.info("Propagating handle to user service");
    }

    @Override
    public void createNewProfile(ProfileCreateRequest profileCreateRequest) {
        log.info("Creating new profile in user service");
    }
}
