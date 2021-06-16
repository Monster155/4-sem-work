package ru.itlab.sem.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itlab.sem.converters.NameToImageConverter;
import ru.itlab.sem.converters.NicknameToUserConverter;
import ru.itlab.sem.dto.postDTO.PostDTO;
import ru.itlab.sem.dto.postDTO.User4PostDTO;
import ru.itlab.sem.dto.userDTO.UserProfileDTO;
import ru.itlab.sem.models.Image;
import ru.itlab.sem.models.Relationship;
import ru.itlab.sem.models.User;
import ru.itlab.sem.services.RelationshipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@Controller
@Slf4j
public class DefaultController {
    @Autowired
    private RelationshipService relService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NicknameToUserConverter nicknameToUserConverter;
    @Autowired
    private NameToImageConverter nameToImageConverter;

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
    public String profile(RedirectAttributes redirectAttributes,
                          @PathVariable("profile") String profile,
                          ModelMap map) {
        //get profile from DB
        log.info(profile);
        User user = nicknameToUserConverter.convert(profile);
        if (user == null) {
            throw new NullPointerException("User's profile not found");
        }
        log.info(user.toString());
        UserProfileDTO profileDTO = modelMapper.map(user, UserProfileDTO.class);
        //add to Model Map
        map.put("profile", profileDTO);

        // post DTO - for creating new post
        PostDTO postDTO = PostDTO.builder()
                .owner(modelMapper.map(user, User4PostDTO.class))
                .build();
        map.put("post", postDTO);
        //show
        return "profile";
    }

    @GetMapping("/follow")
    public String profileFollow(@RequestParam("profile") String profile,
                                ModelMap map) {
        log.info(profile);
        User followedUser = nicknameToUserConverter.convert(profile);
        if (followedUser == null) {
            throw new NullPointerException("User's profile not found");
        }
        log.info(followedUser.toString());

        User user = (User) request.getSession().getAttribute("userModel");
        if (user.getId() != followedUser.getId()) {
            Relationship rel = relService.find(user.getId(), followedUser.getId());
            prepareModelMap(map, rel.getRelation());
        }

        return "d_followBtn";
    }

    @GetMapping("/changeRel")
    public String changeRelationship(@RequestParam("profile") String profile,
                                     ModelMap map) {
        log.info(profile);
        User followedUser = nicknameToUserConverter.convert(profile);
        if (followedUser == null) {
            throw new NullPointerException("User's profile not found");
        }
        log.info(followedUser.toString());

        User user = (User) request.getSession().getAttribute("userModel");

        if (user.getId() != followedUser.getId()) {
            Relationship rel = relService.change(user.getId(), followedUser.getId());
            prepareModelMap(map, rel.getRelation());
        }

        return "d_followBtn";
    }

    private void prepareModelMap(ModelMap map, Relationship.Relations rel) {
        switch (rel) {
            case NONE:
                map.put("text", "Follow");
                break;
            case SECOND_FOLLOWER_OF_FIRST:
                map.put("text", "Add Friend");
                break;
            case FIRST_FOLLOWER_OF_SECOND:
                map.put("text", "Unfollow");
                break;
            case FRIENDS:
                map.put("text", "Remove Friend");
                break;
            default:
        }
    }

    @GetMapping("/getProfile")
    public String getProfile(@RequestParam("profileNick") String profileNickname) {
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#profile").build() + profileNickname;
    }

    @GetMapping("/getOwnProfile")
    public String getOwnProfile() {
        return "redirect:/" + ((User) request.getSession().getAttribute("userModel")).getNickname();
    }

    @RequestMapping("/change")
    public String change(@RequestParam String locale, HttpServletRequest req) {
        String referer = req.getHeader("Referer");
        String[] localeData = locale.split("-");
        localeResolver.setLocale(request, response, new Locale(localeData[0], localeData[1]));
        return "redirect:" + referer;
    }

    @RequestMapping("/logout/after")
    public String logoutAfter() {
        request.getSession().setAttribute("userModel", null);
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#join").build();
    }

    @RequestMapping(value = "/images")
    @ResponseBody
    public void loadImage(@RequestParam("name") String name,
                          HttpServletResponse response) throws IOException, NullPointerException {

        Image image = nameToImageConverter.convert(name);
        if (image == null) image = nameToImageConverter.convert("plug");

        response.setContentType("image/*");
        InputStream in = new ByteArrayInputStream(image.getPhoto());
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping("/favicon.ico")
    public void loadFavicon() throws IOException {
//        Image image = nameToImageConverter.convert("favicon.ico");
//
//        response.setContentType("image/*");
//        InputStream in = new ByteArrayInputStream(image.getPhoto());
//        IOUtils.copy(in, response.getOutputStream());
    }
}
