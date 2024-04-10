package ru.orobtsovv.authservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "_external_account")
public class ExternalEntity extends AccountEntity {
    @Column(name = "client_id", nullable = false, unique = true)
    private String clientId;

    @Column(name = "hashed_secret")
    private String clientSecret;
}
