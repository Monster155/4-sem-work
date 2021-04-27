package ru.itlab.sem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String index() {
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
