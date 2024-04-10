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
@Table(name = "student")
public class StudentEntity extends AccountEntity {
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "hashed_pwd")
    private String hashedPassword;

    @Column(name = "tg_handle")
    private String telegramHandle;
}
