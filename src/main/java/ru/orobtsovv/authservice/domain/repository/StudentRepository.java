package ru.orobtsovv.authservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.authservice.domain.entity.StudentEntity;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    Optional<StudentEntity> findByEmail(String email);

    @Query("select s from StudentEntity s " +
            "where s.userId=:userid")
    Optional<StudentEntity> findByUserid(int userid);

    @Query("update StudentEntity a " +
            "set a.hashedPassword = :hashedPassword " +
            "where a.email = :email")
    @Modifying
    @Transactional
    int updatePassword(String email, String hashedPassword);
}
