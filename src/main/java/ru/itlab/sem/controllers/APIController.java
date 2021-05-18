package ru.itlab.sem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itlab.sem.models.User;
import ru.itlab.sem.services.UserService;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private UserService userService;

    @RequestMapping("/all/getUser")
    public ResponseEntity<User> getUser(@RequestParam("id") Long id) {
        User user = userService.findUserById(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
