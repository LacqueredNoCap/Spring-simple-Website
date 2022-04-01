package com.github.lacquerednocap.repository;

import com.github.lacquerednocap.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByTag(String tag);
}
