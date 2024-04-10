package ru.orobtsovv.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.orobtsovv.authservice.dto.validator.ValidEmail;
import ru.orobtsovv.authservice.utils.Constants;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SignUpTelegramRequest {
    @NotBlank
    private String emailCode;

    @ValidEmail
    private String email;

    private String telegramHandle;

    @NotBlank(message = Constants.NICKNAME_MESSAGE)
    @Size(min = 2, max = 32, message = Constants.NICKNAME_MESSAGE)
    private String nickname;
}
