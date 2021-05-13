package ru.itlab.sem.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itlab.sem.dto.UserRegDTO;
import ru.itlab.sem.models.User;
import ru.itlab.sem.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/sign")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    private MessageSourceAccessor msa;

    @Autowired
    private void setMessageSourceAccessor(MessageSource ms) {
        this.msa = new MessageSourceAccessor(ms);
    }

    @GetMapping
    public String join(ModelMap map) {
        if (!map.containsAttribute("userIn"))
            map.put("userIn", new User());
        if (!map.containsAttribute("userUp"))
            map.put("userUp", new User());
        return "login";
    }

    @RequestMapping("/in/suc")
    public String loginPost(@ModelAttribute User user) {
        System.out.println(user);
        request.getSession().setAttribute("user", user);
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#messages").build();
    }

    @GetMapping("/in/failed")
    public String loginFailed(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", msa.getMessage("login.notfound"));
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#login").build();
    }

    @GetMapping("/up")
    public String reg(ModelMap map) {
        if (!map.containsAttribute("user"))
            map.put("user", new User());
        return "reg";
    }

    @PostMapping("/up")
    public String regPost(RedirectAttributes redirectAttributes,
                          @Valid @ModelAttribute("userUp") UserRegDTO userRegDTO,
                          BindingResult result,
                          ModelMap map) {
        System.out.println(userRegDTO);
        if (result.hasErrors()) {
            System.out.println("failed");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userUp", result);
            redirectAttributes.addFlashAttribute("userUp", userRegDTO);
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#login").build();
        }
        System.out.println("passed");

        if (userService.findUserByEmail(userRegDTO.getEmail()) == null) {
            User user = modelMapper.map(userRegDTO, User.class);
            System.out.println(user);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            user.setRoles(Collections.singleton(new Role(Role.Names.USER))); TODO

            userService.addUser(user);

            request.getSession().setAttribute("user", user);
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#messages").build();
        }

        redirectAttributes.addFlashAttribute("message", msa.getMessage("reg.failed"));
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#login").build();
    }

    @GetMapping("/up_c")
    public String regC(ModelMap map) {
        map.put("user", new User());
        if (request.getSession().getAttribute("user") == null)
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#index").build();
        else {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#messages").build();
        }
    }

    @PostMapping("/up_c")
    public String regCPost(@ModelAttribute("user") UserRegDTO userRegDTO,
                           ModelMap map) {
        System.out.println(userRegDTO);
//        User user = (User) request.getSession().getAttribute("user");
//        modelMapper.map(userRegDTO, user);
//        if (userService.addUser(user))
//            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#messages").build();
//        else
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#index").build();
    }
}
