package ru.orobtsovv.authservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class StudentEntity {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "userid", referencedColumnName="userid")
    private AccountEntity userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "hashed_pwd")
    private String hashedPassword;

    @Column(name = "tg_handle")
    private String telegramHandle;
}
