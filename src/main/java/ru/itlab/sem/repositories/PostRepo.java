package ru.itlab.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itlab.sem.models.Post;

public interface PostRepo extends JpaRepository<Post, Long> {}
