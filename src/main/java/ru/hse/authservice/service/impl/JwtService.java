package ru.hse.authservice.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.hse.authservice.dto.TokenResponse;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService {
    private final KeyService keyService;

    public TokenResponse refresh(String refresh) {
        log.info("refresh");
        Algorithm algorithm = Algorithm.RSA256(
                keyService.getPublicKey(), keyService.getPrivateKey());
        String token = JWT.create()
                .withIssuer("auth0")
                .withIssuedAt(Instant.now())
                .withClaim("username", "stas")
                .withClaim("userid", 100500)
                .withClaim("roles", List.of("ROLE_USER"))
                .withExpiresAt(Instant.now().plusSeconds(20000))
                .sign(algorithm);

        return new TokenResponse(token, refresh, 100500);
    }
}
