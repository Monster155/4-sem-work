package ru.itlab.sem.converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itlab.sem.models.User;
import ru.itlab.sem.services.UserService;

@Slf4j
@Component
public class NicknameToUserConverter implements Converter<String, User> {

    @Autowired
    private UserService userService;

    @Override
    public User convert(String s) {
        try {
            return userService.findUserById(Long.parseLong(s));
        } catch (NumberFormatException e) {
            log.info("Can't convert '" + s + "' to Long, try to find it from Database");
            return userService.findUserByNickname(s);
        }
    }
}
