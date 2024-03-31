package ru.orobtsovv.authservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BannedEmailEntity {
    @Id
    private String email;

    @Column(name = "banned_until")
    private LocalDateTime bannedUntil;

    @Column
    private String reason;

    @Column
    private int moderatorId;
}

