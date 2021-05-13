package ru.itlab.sem.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itlab.sem.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void addUser(User user);

    User getUserById(Long id);

    User findUserByEmail(String email);

    List<User> getAll();

}
