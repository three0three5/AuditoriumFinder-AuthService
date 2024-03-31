package ru.orobtsovv.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.authservice.domain.entity.AccountEntity;
import ru.orobtsovv.authservice.domain.entity.RefreshTokenEntity;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshTokenEntity, String> {
    @Modifying
    @Transactional
    @Query("delete from RefreshTokenEntity r " +
            "where r.isTelegramSession " +
            "and r.accountEntity.userId = :userid " +
            "and r.usedAt is null " +
            "and r.validUntil > current_timestamp")
    int deleteActiveTelegramSession(int userid);

    @Modifying
    @Transactional
    @Query("delete from RefreshTokenEntity r " +
            "where r.accountEntity.userId = :userid " +
            "and r.usedAt is null " +
            "and r.validUntil > current_timestamp")
    int removeAllActive(int userid);

    int deleteAllByAccountEntity(AccountEntity account);
}
