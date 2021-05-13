package ru.itlab.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itlab.sem.models.Image;

public interface ImageRepo extends JpaRepository<Image, Long> {}
