package ru.itlab.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itlab.sem.models.Relationship;

@Repository
public interface RelationshipRepo extends JpaRepository<Relationship, Long> {

    @Query("select r from Relationship r where (r.user1.id = ?1 and r.user2.id = ?2) or (r.user1.id = ?2 and r.user2.id = ?1) ")
    Relationship findRelationship(long user1_id, long user2_id);

    /*
    @Query(value = "select user_id, followers_id as other_id from users_followers where user_id=:user_id and followers_id=:follower_id ;", nativeQuery = true)
    Relationship findFollower(@Param("user_id") Long user_id, @Param("follower_id") Long follower_id);

    @Query(value = "insert into users_followers values (:user_id, :follower_id) returning *;", nativeQuery = true)
    Relationship addFollower(@Param("user_id") Long user_id, @Param("follower_id") Long follower_id);

    @Query(value = "delete from users_followers where user_id=:user_id and followers_id=:follower_id returning *;", nativeQuery = true)
    Relationship removeFollower(@Param("user_id") Long user_id, @Param("follower_id") Long follower_id);

    @Query(value = "select user_id, friends_id as other_id from users_friends where user_id=:user_id and friends_id=:friend_id ;", nativeQuery = true)
    Relationship findFriend(@Param("user_id") Long user_id, @Param("friend_id") Long friend_id);

    @Query(value = "insert into users_friends values (:user_id, :friend_id) returning *;", nativeQuery = true)
    Relationship addFriend(@Param("user_id") Long user_id, @Param("friend_id") Long friend_id);

    @Query(value = "delete from users_friends where user_id=:user_id and friends_id=:friend_id returning *;", nativeQuery = true)
    Relationship removeFriend(@Param("user_id") Long user_id, @Param("friend_id") Long friend_id);
    */
}
