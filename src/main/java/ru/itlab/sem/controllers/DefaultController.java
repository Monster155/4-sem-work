package ru.itlab.sem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itlab.sem.services.UserService;

@Controller
//@ComponentScan(basePackages = {"ru.itlab.sem"})
public class DefaultController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        // if authorized - redirect messages
        // else - redirect login (user login everytime, but register only once)
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#messages").build();
    }

    @GetMapping("/m")
    public String messages() {
        return "messages";
    }

    @GetMapping("/{profile}")
    public String profile(@PathVariable("profile") String profile) {
        //get profile from DB
        //add to Model Map
        //show
        return "profile";
    }
}
