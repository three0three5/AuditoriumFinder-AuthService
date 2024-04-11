package ru.orobtsovv.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.orobtsovv.authservice.domain.entity.BannedEmailEntity;

import java.util.Optional;

@Repository
public interface BlacklistRepository extends JpaRepository<BannedEmailEntity, String> {
    @Query("select b from BannedEmailEntity as b " +
            "where b.email=:email and " +
            "(b.bannedUntil is null or b.bannedUntil < current_timestamp)")
    Optional<BannedEmailEntity> findValidByEmail(String email);
}
