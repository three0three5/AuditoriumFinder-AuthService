package ru.orobtsovv.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.orobtsovv.authservice.dto.validator.ValidEmail;

@Data
@NoArgsConstructor
public class EmailWithCodeRequest {
    @ValidEmail
    private String email;

    @NotBlank
    private String code;
}
