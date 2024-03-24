package ru.hse.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.hse.authservice.dto.validator.ValidEmail;
import ru.hse.authservice.dto.validator.ValidProofKey;

import static ru.hse.authservice.utils.Constants.NICKNAME_MESSAGE;
import static ru.hse.authservice.utils.Constants.TELEGRAM_PROVIDED;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SignUpTelegramRequest {
    @ValidProofKey
    private String proofKey;

    @ValidEmail
    private String email;

    @NotBlank(message = TELEGRAM_PROVIDED)
    private String telegramHandle;

    @NotBlank(message = NICKNAME_MESSAGE)
    @Size(min = 2, max = 32, message = NICKNAME_MESSAGE)
    private String nickname;
}
