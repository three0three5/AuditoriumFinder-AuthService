package ru.orobtsovv.authservice.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class EmailProperties {
    @Value("${email-params.code.lifespan-seconds}")
    private final long codeLifespan;
    @Value("${email-params.code.length}")
    private final int codeLength;
}
