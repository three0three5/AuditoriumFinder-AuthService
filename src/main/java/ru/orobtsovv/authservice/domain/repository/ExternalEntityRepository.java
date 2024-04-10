package ru.orobtsovv.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orobtsovv.authservice.domain.entity.AccountEntity;
import ru.orobtsovv.authservice.domain.entity.ExternalEntity;

public interface ExternalEntityRepository extends JpaRepository<ExternalEntity, AccountEntity> {
}
