package ru.orobtsovv.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.orobtsovv.authservice.dto.validator.ValidEmail;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SignInTelegramRequest {
    @NotBlank
    private String emailCode;

    @ValidEmail
    private String email;

    private String telegramHandle;
}
