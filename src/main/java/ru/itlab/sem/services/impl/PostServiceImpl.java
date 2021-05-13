package ru.itlab.sem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.sem.services.PostService;
import ru.itlab.sem.repositories.PostRepo;
import ru.itlab.sem.models.Post;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Override
    public void addPost(Post post) {
        postRepo.saveAndFlush(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return postRepo.findAll();
    }

}
