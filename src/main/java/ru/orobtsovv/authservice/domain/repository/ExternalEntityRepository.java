package ru.orobtsovv.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.orobtsovv.authservice.domain.entity.ExternalEntity;

import java.util.Optional;

@Repository
public interface ExternalEntityRepository extends JpaRepository<ExternalEntity, Integer> {
    Optional<ExternalEntity> findByClientId(String clientId);
}
