package ru.orobtsovv.authservice.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class AuthProperties {
    @Value("${auth-params.jwt.lifespan-seconds}")
    private final long jwtLifespan;
    @Value("${auth-params.refresh.lifespan-days}")
    private final long refreshLifespan;
    @Value("${auth-params.refresh.length}")
    private final int refreshLength;
}
