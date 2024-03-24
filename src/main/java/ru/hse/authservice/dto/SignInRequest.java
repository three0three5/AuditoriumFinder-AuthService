package ru.hse.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.hse.authservice.dto.validator.ValidEmail;
import ru.hse.authservice.dto.validator.ValidPassword;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    @ValidEmail
    private String email;

    @ValidPassword
    private String password;
}
