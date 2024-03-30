package ru.orobtsovv.authservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.orobtsovv.authservice.dto.validator.ValidEmail;

@Data
@NoArgsConstructor
public class EmailRequest {
    @ValidEmail
    private String email;
}
