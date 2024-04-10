package ru.orobtsovv.authservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.dto.response.RsaPublicKeyResponse;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

@Service
@Slf4j
public class KeyService {
    private final KeyPair pair;

    public KeyService() {
        try {
            var generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            pair = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public RsaPublicKeyResponse getPublicSignatureKey() {
        RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();
        String modulus = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(publicKey.getModulus().toByteArray());
        String exponent = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(publicKey.getPublicExponent().toByteArray());
        return new RsaPublicKeyResponse()
                .setExponent(exponent)
                .setModulus(modulus)
                .setKeyType(publicKey.getAlgorithm());
    }

    public RSAPublicKey getPublicKey() {
        return (RSAPublicKey) pair.getPublic();
    }

    RSAPrivateKey getPrivateKey() {
        return (RSAPrivateKey) pair.getPrivate();
    }
}
