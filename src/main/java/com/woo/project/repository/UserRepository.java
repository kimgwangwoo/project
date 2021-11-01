package com.woo.project.repository;

import com.woo.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String username);

        @Transactional
        @Modifying
        @Query(value = "UPDATE user"
                + " SET enabled= ?2 "
                + " WHERE id = ?1", nativeQuery = true)
        void updateEnabled(Long id, boolean enabled);
}
