package ru.orobtsovv.authservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileCreateRequest {
    private Integer id;
    private String nickname;
    private String email;
    private String telegramHandler;
}

