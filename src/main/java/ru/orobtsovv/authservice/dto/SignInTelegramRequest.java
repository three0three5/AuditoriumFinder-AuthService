package ru.orobtsovv.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.orobtsovv.authservice.dto.validator.ValidEmail;

import static ru.orobtsovv.authservice.utils.Constants.TELEGRAM_PROVIDED;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SignInTelegramRequest {
    @NotBlank
    private String emailCode;

    @ValidEmail
    private String email;

    @NotBlank(message = TELEGRAM_PROVIDED)
    private String telegramHandle;
}
