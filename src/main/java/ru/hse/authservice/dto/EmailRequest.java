package ru.hse.authservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.authservice.dto.validator.ValidEmail;

@Data
@NoArgsConstructor
public class EmailRequest {
    @ValidEmail
    private String email;
}
