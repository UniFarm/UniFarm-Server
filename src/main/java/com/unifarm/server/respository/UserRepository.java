package com.unifarm.server.respository;

import com.unifarm.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailAndPassword(final String email, final String password);
    User findByUserIdx(int userIdx);
    Optional<User> findByNickname(String name);
    Optional<User> findByEmail(String email);
}