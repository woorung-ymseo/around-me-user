package com.around.me.user.api.v1.user.repository;

import com.around.me.user.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNo(long l);

    Optional<User> findByUserEmail(String userEmail);
}


