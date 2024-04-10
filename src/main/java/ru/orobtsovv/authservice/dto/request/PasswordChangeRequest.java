package ru.orobtsovv.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.orobtsovv.authservice.dto.validator.ValidEmail;
import ru.orobtsovv.authservice.dto.validator.ValidPassword;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequest {
    @ValidEmail
    private String email;
    @NotBlank
    private String emailCode;
    @ValidPassword
    private String newPassword;
}
