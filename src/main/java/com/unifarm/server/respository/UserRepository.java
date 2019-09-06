package com.unifarm.server.respository;

import com.unifarm.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserIdx(int userIdx);
    Optional<User> findByEmailAndPassword(final String email, final String password);
    Optional<User> findByEmail(String email);
}