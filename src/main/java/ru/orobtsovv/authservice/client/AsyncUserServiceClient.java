package ru.orobtsovv.authservice.client;

import ru.orobtsovv.authservice.dto.request.ProfileCreateRequest;

public interface AsyncUserServiceClient {
    void propagateHandle(int userid, String telegramHandle);

    void createNewProfile(ProfileCreateRequest profileCreateRequest);

    void removeTgHandle(int userId);
}
