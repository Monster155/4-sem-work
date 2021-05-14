package ru.itlab.sem.controllers;

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
import ru.itlab.sem.converters.NicknameConverter;
import ru.itlab.sem.models.Post;
import ru.itlab.sem.services.PostService;
import ru.itlab.sem.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class DefaultController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NicknameConverter nicknameConverter;

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
    public String profile(@PathVariable("profile") String profile) {
        //get profile from DB
        //add to Model Map
        //show
        return "profile";
    }

    @RequestMapping("/change")
    public String change(@RequestParam String locale, HttpServletRequest req) {
        String referer = req.getHeader("Referer");
        String[] localeData = locale.split("-");
        localeResolver.setLocale(request, response, new Locale(localeData[0], localeData[1]));
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/images")
    @ResponseBody
    public void loadImage(@RequestParam("name") String name,
                          HttpServletResponse response) throws IOException, NullPointerException {
        File imageFile = new File("D:\\Projects\\Intelij IDEA Projects\\sem-4\\src\\main\\webapp\\images\\background.png");
        response.setContentType("image/png");
        InputStream in = new FileInputStream(imageFile);
        IOUtils.copy(in, response.getOutputStream());
    }

    @RequestMapping("/getPost")
    public String loadPost(@RequestParam("id") Long id,
                           ModelMap map) {
        Post post = postService.getPostById(id);
        System.out.println(post);
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);

        map.put("posts", posts);

        System.out.println(map);
//        map.put("post.musics", post.get);

        //${profile.photo}
        //${profile.name}
        //${post.date}
        //${post.images}
        //${post.text}
        //${post.musics}
        return "d_post";
    }

    @RequestMapping("/getAllPosts")
    public String loadAllPosts(@RequestParam("userid") String id,
                               @RequestParam("count") Long count,
                               ModelMap map) {

        Long userId = nicknameConverter.convert(id);
        if (userId == null) return "";

        List<Post> posts = postService.getAllUserPosts(userId, Math.toIntExact(count));

        map.put("posts", posts);

        //${profile.photo}
        //${profile.name}
        //${post.date}
        //${post.images}
        //${post.text}
        //${post.musics}
        return "d_post";
    }
}
