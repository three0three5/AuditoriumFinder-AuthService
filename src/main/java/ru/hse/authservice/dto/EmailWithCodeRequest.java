package ru.hse.authservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.authservice.dto.validator.ValidEmail;
import ru.hse.authservice.dto.validator.ValidProofKey;

@Data
@NoArgsConstructor
public class EmailWithCodeRequest {
    @ValidEmail
    private String email;

    @ValidProofKey
    private String proofKey;
}
