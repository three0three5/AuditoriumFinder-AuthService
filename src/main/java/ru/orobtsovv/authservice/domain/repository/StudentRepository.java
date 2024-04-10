package ru.orobtsovv.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orobtsovv.authservice.domain.entity.AccountEntity;
import ru.orobtsovv.authservice.domain.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, AccountEntity> {
}
