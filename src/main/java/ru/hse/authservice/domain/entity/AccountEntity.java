package ru.hse.authservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "_account")
public class AccountEntity {
    @Id
    @GeneratedValue
    @Column(name = "userid")
    private int userId;

    @Column(name = "telegram")
    private String telegramHandle;

    @Column(name = "email")
    private String email;

    @Column(name = "is_banned")
    private boolean isBanned;

    @Column(name = "banned_until")
    private LocalDateTime bannedUntil;
}
