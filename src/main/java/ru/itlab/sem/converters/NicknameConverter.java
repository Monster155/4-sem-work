package ru.itlab.sem.converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itlab.sem.models.User;
import ru.itlab.sem.services.UserService;

@Slf4j
@Component
public class NicknameConverter implements Converter<String, Long> {

    @Autowired
    private UserService userService;

    @Override
    public Long convert(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            log.info("Can't convert '" + s + "' to Long, try to find it from Database");

            User user = userService.findUserByNickname(s);
            if (user == null) {
                log.info("User with nickname '" + s + "' not found, returning null...");
                return null;
            }
            return user.getId();
        }
    }
}
