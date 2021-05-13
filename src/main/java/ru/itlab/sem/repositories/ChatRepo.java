package ru.itlab.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itlab.sem.models.Chat;

public interface ChatRepo extends JpaRepository<Chat, Long> {}
