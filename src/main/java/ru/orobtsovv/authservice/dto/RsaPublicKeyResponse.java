package ru.orobtsovv.authservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RsaPublicKeyResponse {
    @JsonProperty("kty")
    private String keyType;

    @JsonProperty("n")
    private String modulus;

    @JsonProperty("e")
    private String exponent;
}
