package ru.itlab.sem.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itlab.sem.dto.userDTO.UserApiDTO;
import ru.itlab.sem.models.User;
import ru.itlab.sem.services.UserService;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping("/all/getUser")
    public ResponseEntity<UserApiDTO> getUser(@RequestParam("id") Long id) {
        User user = userService.findUserById(id);
        UserApiDTO userApiDTO = modelMapper.map(user, UserApiDTO.class);

        return user != null
                ? new ResponseEntity<>(userApiDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
