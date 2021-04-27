package ru.itlab.sem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.sem.repositories.UserRepo;
import ru.itlab.sem.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
}
