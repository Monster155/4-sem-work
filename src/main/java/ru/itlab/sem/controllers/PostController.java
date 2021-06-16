package ru.itlab.sem.controllers;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
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
@Slf4j
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
//    @ResponseBody
    public String addPost(@RequestParam("images") MultipartFile[] multipartFile,
                          @ModelAttribute("post") PostAddDTO postAddDTO,
                          BindingResult result,
                          ModelMap map) {

        if (result.hasErrors()) {
            log.info(result.getAllErrors().toString());
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#getOwnProfile").build();
        }

        ArrayList<Image> images = new ArrayList<>();
        for (MultipartFile mFile : multipartFile) {
            Image image = imageService.addImage(mFile, null);
            images.add(image);
            log.info(image.toString());
        }

        Post post = modelMapper.map(postAddDTO, Post.class);
        post.setOwner((User) request.getSession().getAttribute("userModel"));
        post.setImages(images);

        post = postService.addPost(post);
        log.info(post.toString());

        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#getOwnProfile").build();
    }

    @RequestMapping("/get")
    public String loadPost(@RequestParam("id") Long id,
                           ModelMap map) {
        Post post = postService.getPostById(id);
        log.info(post.toString());
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        ArrayList<PostDTO> posts = new ArrayList<>();
        posts.add(postDTO);
        map.put("posts", posts);

        return "d_post";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Boolean deletePost(@RequestParam("id") Long id,
                             ModelMap map) {

        postService.deletePost(id);

        return true;
    }

    @RequestMapping("/update")
    public String updatePost(@ModelAttribute Post post,
                             ModelMap map) {
        log.info(post.toString());
        post = postService.updatePost(post);
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        map.put("post", postDTO);

        return "d_post";
    }

    @RequestMapping("/getAll")
    public String loadAllPosts(@RequestParam("userid") String id,
                               @RequestParam("count") String count,
                               ModelMap map) {

        log.info(id + " " + count);
        User user = nicknameToUserConverter.convert(id);
        log.info(user.toString());
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
