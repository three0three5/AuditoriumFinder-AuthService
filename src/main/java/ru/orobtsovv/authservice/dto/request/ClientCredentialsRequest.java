package ru.orobtsovv.authservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientCredentialsRequest {
    private String clientId;
    private String clientSecret;
}
