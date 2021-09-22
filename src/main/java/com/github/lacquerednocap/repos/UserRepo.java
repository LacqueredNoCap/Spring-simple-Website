package com.github.lacquerednocap.repos;

import com.github.lacquerednocap.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
