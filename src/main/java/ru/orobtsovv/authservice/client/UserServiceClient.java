package ru.orobtsovv.authservice.client;

import ru.orobtsovv.authservice.dto.ProfileCreateRequest;

public interface UserServiceClient {
    void propagateHandle(int userid, String telegramHandle);

    void createNewProfile(ProfileCreateRequest profileCreateRequest);
}
