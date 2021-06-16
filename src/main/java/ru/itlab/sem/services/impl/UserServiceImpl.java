package ru.itlab.sem.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itlab.sem.models.User;
import ru.itlab.sem.repositories.UserRepo;
import ru.itlab.sem.services.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User addUser(User user) {
        //StringEscapeUtils.escapeHtml4

        try {
            // get all declared fields
            Field[] fields = user.getClass().getDeclaredFields();
            for (Field field : fields) {
                // if it is a String field
                if (field.getType().equals(String.class)) {
                    field.setAccessible(true);
                    // get field name
                    String fieldName = field.getName();
                    // generate field's Set method name
                    String upperFieldName = ((Character) fieldName.charAt(0)).toString().toUpperCase().charAt(0) + fieldName.substring(1);
                    Method m = user.getClass().getMethod("set" + upperFieldName, String.class);
                    // user field's Set method
                    m.invoke(user, StringEscapeUtils.escapeHtml4((String) field.get(user)));
                    field.setAccessible(false);
                }
            }
            log.info(user.toString());
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.info("UserServiceImpl#addUser: " + e.getMessage());
        }

        return userRepo.saveAndFlush(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User findUserByNickname(String nickname) {
        return userRepo.findUserByNickname(nickname);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("load user by username");
        return findUserByEmail(s);
    }
}
