package ru.itlab.sem.services;

public interface RelationshipService {

    Relationship add(Long user_id, Long other_id);

    Relationship remove(Long user_id, Long other_id);

    Relationship find(Long user_id, Long other_id);

    Relationship change(Long user_id, Long other_id);

    enum Relationship {
        none,
        otherFollower,
        userFollower,
        friends,
        youOwn
    }
}
