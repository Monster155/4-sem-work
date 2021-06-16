package ru.itlab.sem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itlab.sem.models.Post;
import ru.itlab.sem.repositories.PostRepo;
import ru.itlab.sem.services.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Override
    public Post addPost(Post post) {
        return postRepo.saveAndFlush(post);
    }

    @Override
    public Post updatePost(Post post) {
        return postRepo.updatePost(post.getText(), post.getId());
    }

    @Override
    public Post deletePost(Long id) {
        return postRepo.deletePostById(id);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return postRepo.findAll();
    }

    @Override
    public List<Post> getAllUserPosts(Long userId, Integer limit) {
        return postRepo.loadAllPosts(userId, PageRequest.of(0, limit));
    }

}
