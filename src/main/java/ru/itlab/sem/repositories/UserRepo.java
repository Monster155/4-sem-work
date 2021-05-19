package ru.itlab.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itlab.sem.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserByNickname(String nickname);

    @Query(value = "insert into users_followers values (:user_id, :follower_id);", nativeQuery = true)
    void addFollower(@Param("user_id") Long user_id, @Param("follower_id") Long follower_id);

    @Query(value = "insert into users_friends values (:user_id, :friend_id);", nativeQuery = true)
    void addFriend(@Param("user_id") Long user_id, @Param("friend_id") Long friend_id);
}
