package com.github.lacquerednocap.repos;

import com.github.lacquerednocap.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
}
