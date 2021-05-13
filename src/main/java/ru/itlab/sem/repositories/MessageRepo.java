package ru.itlab.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itlab.sem.models.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {}
