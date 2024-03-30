package ru.orobtsovv.authservice.domain.entity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@RedisHash("EmailCode")
@Getter
@Setter
@NoArgsConstructor
public class EmailCodeEntity implements Serializable {
    @Id
    @Column(name = "email")
    private String email;
    private String code;
    @TimeToLive
    private Long expiration;
}
