package ru.itlab.sem.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itlab.sem.converters.NameToImageConverter;
import ru.itlab.sem.converters.NicknameToUserConverter;
import ru.itlab.sem.dto.postDTO.PostAddDTO;
import ru.itlab.sem.dto.postDTO.PostDTO;
import ru.itlab.sem.models.Image;
import ru.itlab.sem.models.Post;
import ru.itlab.sem.models.User;
import ru.itlab.sem.services.ImageService;
import ru.itlab.sem.services.PostService;
import ru.itlab.sem.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
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

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @PostMapping("/add")
    @ResponseBody
    public String addPost(@RequestParam("images") MultipartFile[] multipartFile,
                          @ModelAttribute("post") PostAddDTO postAddDTO,
                          BindingResult result,
                          ModelMap map) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "post errors";
        }

        ArrayList<Image> images = new ArrayList<>();
        for (MultipartFile mFile : multipartFile) {
            Image image = imageService.addImage(mFile, null);
            images.add(image);
            System.out.println(image);
        }

        Post post = modelMapper.map(postAddDTO, Post.class);
        post.setOwner((User) request.getSession().getAttribute("userModel"));
        post.setImages(images);

        post = postService.addPost(post);
        System.out.println(post);
        return "done";
    }

    @RequestMapping("/get")
    public String loadPost(@RequestParam("id") Long id,
                           ModelMap map) {
        Post post = postService.getPostById(id);
        System.out.println(post);
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        ArrayList<PostDTO> posts = new ArrayList<>();
        posts.add(postDTO);
        map.put("posts", posts);

        return "d_post";
    }

    @RequestMapping("/getAll")
    public String loadAllPosts(@RequestParam("userid") String id,
                               @RequestParam("count") String count,
                               ModelMap map) {

        System.out.println(id + " " + count);
        User user = nicknameToUserConverter.convert(id);
        System.out.println(user);
        if (user == null) {
            return "d_post";
        }

        List<PostDTO> posts = postService.getAllUserPosts(user.getId(), Integer.parseInt(count)).stream()
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
