package ru.hse.authservice.domain.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.authservice.domain.entity.RefreshTokenEntity;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshTokenEntity, String> {
}
