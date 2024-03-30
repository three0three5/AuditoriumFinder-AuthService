package ru.orobtsovv.authservice.domain.redis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.orobtsovv.authservice.domain.entity.EmailCodeEntity;

@Repository
public interface EmailCodeRepository extends JpaRepository<EmailCodeEntity, String> {
}
