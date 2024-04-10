package ru.orobtsovv.authservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class JwtTokenResponse {
    private String jwt;
}
