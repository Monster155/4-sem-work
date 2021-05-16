package ru.itlab.sem.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itlab.sem.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User addUser(User user);

    User findUserById(Long id);

    User findUserByEmail(String email);

    List<User> getAll();

    User findUserByNickname(String nickname);

}
