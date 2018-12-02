package com.sinam.repository;

import com.sinam.model.Role;
import com.sinam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

//    SELECT * FROM users WHERE is_active = false;
    List<User> findByActiveIsFalse();

    /**
     * При кастомной модификации базы jpa требует аннотаций Modifying и Transactional
     * @param userId
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE User u SET u.active = :status WHERE u.id = :userId")
    int changeUserStatus(@Param("status") boolean param, @Param("userId") Long userId);

}