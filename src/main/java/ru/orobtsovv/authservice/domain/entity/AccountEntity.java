package ru.orobtsovv.authservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Table(name = "_account")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AccountEntity {
    @Id
    @GeneratedValue
    @Column(name = "userid")
    private int userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private AccountRole accountRole;
}
