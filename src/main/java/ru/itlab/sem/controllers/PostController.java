package ru.itlab.sem.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itlab.sem.converters.NameToImageConverter;
import ru.itlab.sem.converters.NicknameToUserConverter;
import ru.itlab.sem.dto.PostDTO;
import ru.itlab.sem.models.Post;
import ru.itlab.sem.models.User;
import ru.itlab.sem.services.ImageService;
import ru.itlab.sem.services.PostService;
import ru.itlab.sem.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/post")
public class PostController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private ImageService imageService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NicknameToUserConverter nicknameToUserConverter;
    @Autowired
    private NameToImageConverter nameToImageConverter;

    @RequestMapping("/get")
    public String loadPost(@RequestParam("id") Long id,
                           ModelMap map) {
        Post post = postService.getPostById(id);
        System.out.println(post);

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);
        map.put("posts", posts);

        return "d_post";
    }

    @RequestMapping("/getAll")
    public String loadAllPosts(@RequestParam("userid") String id,
                               @RequestParam("count") Long count,
                               ModelMap map) {

        User user = nicknameToUserConverter.convert(id);
        if (user == null) {
            throw new NullPointerException("User's posts not found");
        }

        List<PostDTO> posts = postService.getAllUserPosts(user.getId(), Math.toIntExact(count)).stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        map.put("posts", posts);
        return "d_post";
    }

    //${profile.photo}
    //${profile.name}
    //${post.date}
    //${post.images}
    //${post.text}
    //${post.musics}
}
