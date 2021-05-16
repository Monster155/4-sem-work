package ru.itlab.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itlab.sem.models.Image;
import ru.itlab.sem.models.User;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findFirstByName(String name);
}
