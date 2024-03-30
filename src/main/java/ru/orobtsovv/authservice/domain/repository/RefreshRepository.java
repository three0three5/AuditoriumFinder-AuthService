package ru.orobtsovv.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.orobtsovv.authservice.domain.entity.RefreshTokenEntity;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshTokenEntity, String> {
    @Modifying
    @Query("delete from RefreshTokenEntity r " +
            "where r.isTelegramSession and r.accountEntity.userId = :userid")
    int deleteTelegramSession(int userid);
}
