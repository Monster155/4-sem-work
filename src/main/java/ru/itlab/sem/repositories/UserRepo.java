package ru.itlab.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itlab.sem.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserByNickname(String nickname);
}
