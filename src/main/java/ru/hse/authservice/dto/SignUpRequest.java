package ru.hse.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.hse.authservice.dto.validator.ValidEmail;
import ru.hse.authservice.dto.validator.ValidPassword;
import ru.hse.authservice.dto.validator.ValidProofKey;

import static ru.hse.authservice.utils.Constants.NICKNAME_MESSAGE;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @ValidProofKey
    private String proofKey;

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;

    @NotBlank(message = NICKNAME_MESSAGE)
    @Size(min = 2, max = 32, message = NICKNAME_MESSAGE)
    private String nickname;
}
