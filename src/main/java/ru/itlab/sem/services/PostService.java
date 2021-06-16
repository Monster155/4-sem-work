package ru.itlab.sem.services;

import ru.itlab.sem.models.Post;

import java.util.List;

public interface PostService {

   Post addPost(Post post);

    Post updatePost(Post post);

    Post deletePost(Long id);

    Post getPostById(Long id);

   List<Post> getAll();

   List<Post> getAllUserPosts(Long userId, Integer limit);
}
