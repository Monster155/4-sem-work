package ru.itlab.sem.controllers;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itlab.sem.APIs.vk.VKontakteAPIController;
import ru.itlab.sem.dto.userDTO.UserRegConDTO;
import ru.itlab.sem.dto.userDTO.UserRegDTO;
import ru.itlab.sem.models.Image;
import ru.itlab.sem.models.User;
import ru.itlab.sem.services.ImageService;
import ru.itlab.sem.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/sign")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
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
    @Autowired
    private AuthenticationManager authenticationManager;

    private MessageSourceAccessor msa;

    @Autowired
    private void setMessageSourceAccessor(MessageSource ms) {
        this.msa = new MessageSourceAccessor(ms);
    }

    @GetMapping
    public String join(ModelMap map) {
        map.put("oauth", VKontakteAPIController.getURL4Code());
        if (!map.containsAttribute("userIn"))
            map.put("userIn", new User());
        if (!map.containsAttribute("userUp"))
            map.put("userUp", new User());
        return "login";
    }

    @RequestMapping("/in/suc")
    public String loginPost(@ModelAttribute User user) {
        user = userService.findUserByEmail(user.getEmail());
        System.out.println(user);
        request.getSession().setAttribute("userModel", user);
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#messages").build();
    }

    @GetMapping("/in/failed")
    public String loginFailed(RedirectAttributes redirectAttributes) {
        System.out.println("Login failed");
        redirectAttributes.addFlashAttribute("message", msa.getMessage("login.notfound"));
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#join").build();
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
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#join").build();
        }
        System.out.println("passed");

        if (userService.findUserByEmail(userRegDTO.getEmail()) == null) {
            User user = modelMapper.map(userRegDTO, User.class);
            System.out.println(user);

            user.setNickname(user.getNickname().toLowerCase());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            user.setRoles(Collections.singleton(new Role(Role.Names.USER))); TODO

            request.getSession().setAttribute("userModel", user);
            //TODO remove when logout
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#regC").build();
        }

        redirectAttributes.addFlashAttribute("message", msa.getMessage("reg.failed"));
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#join").build();
    }

    @GetMapping("/up_c")
    public String regC(ModelMap map) {
        if (request.getSession().getAttribute("userModel") == null) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#index").build();
        } else {
            map.put("user", new User());
            return "reg_c";
        }
    }

    @PostMapping("/up_c")
    public String regCPost(RedirectAttributes redirectAttributes,
                           @RequestParam("photo") MultipartFile multipartFile,
                           @Valid @ModelAttribute("user") UserRegConDTO userRegConDTO,
                           BindingResult result,
                           ModelMap map) {
        if (result.hasErrors()) {
            System.out.println("failed");
            return "reg_c";
        }

        Image image = imageService.addImage(multipartFile, null);
        System.out.println(image);

        User user = (User) request.getSession().getAttribute("userModel");
        System.out.println(user);
        System.out.println(userRegConDTO);

        user.setPhoto(image);
        user.setImages(new ArrayList<>());
        user.getImages().add(image);
        modelMapper.map(userRegConDTO, user);
        System.out.println(user);

        user = userService.addUser(user);
        request.getSession().setAttribute("userModel", user);

        redirectAttributes.addFlashAttribute("message", "Successfully registered. Now you can login!");
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#join").build();

//        redirectAttributes.addFlashAttribute("user", modelMapper.map(user, UserLoginDTO.class));
//        return "forward:" + "/sign/in";

        // autologin
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//        token.setDetails(new WebAuthenticationDetails(request));
////        Authentication authenticatedUser = authenticationManager.authenticate(token);
////        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
//        SecurityContextHolder.getContext().setAuthentication(token);
//
//        redirectAttributes.addFlashAttribute("user", user);
//        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#loginPost").build();
    }

    @RequestMapping("/oauth")
    @ResponseBody
    public String oAuth(@RequestParam("code") String code) {
        return code;
    }
}
