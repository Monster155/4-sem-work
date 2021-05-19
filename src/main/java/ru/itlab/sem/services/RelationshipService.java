package ru.itlab.sem.services;

public interface RelationshipService {

    Relationship add(Long user_id, Long follower_id);

    Relationship remove(Long user_id, Long follower_id);

    Relationship find(Long user_id, Long follower_id);

    enum Relationship {
        none,
        heFollower,
        youFollowed,
        friends,
        youOwn
    }
}
