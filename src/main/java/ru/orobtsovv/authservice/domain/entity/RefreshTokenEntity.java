package ru.orobtsovv.authservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "_refresh")
public class RefreshTokenEntity {
    @Id
    @Column(name = "refresh_value", nullable = false)
    private String refreshValue;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userid", referencedColumnName="userid")
    private AccountEntity accountEntity;

    @Column(name = "valid_until", nullable = false)
    private LocalDateTime validUntil; // TODO шедулер

    @Column(name = "is_tg")
    private boolean isTelegramSession;

    @Column(name = "used_at")
    private LocalDateTime usedAt;
}
