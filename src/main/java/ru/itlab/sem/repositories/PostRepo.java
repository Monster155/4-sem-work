package ru.itlab.sem.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itlab.sem.models.Post;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.owner.id = ?1")
    List<Post> loadAllPosts(Long userId, Pageable pageable);

    @Query("update Post p set p.text = ?1 where p.id = ?2")
    Post updatePost(String newText, Long id);

    Post deletePostById(Long id);
}
