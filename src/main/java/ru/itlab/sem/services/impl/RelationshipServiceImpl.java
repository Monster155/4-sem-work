package ru.itlab.sem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.sem.repositories.RelationshipRepo;
import ru.itlab.sem.services.RelationshipService;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    private RelationshipRepo repo;

    @Override
    public Relationship add(Long user_id, Long other_id) {
        if (user_id.equals(other_id)) return Relationship.youOwn;

        if (repo.findFollower(user_id, other_id) != null) {
            return Relationship.heFollower;
        } else {
            if (repo.findFollower(other_id, user_id) != null) {
                repo.removeFollower(other_id, user_id);
                repo.addFriend(user_id, other_id);
                repo.addFriend(other_id, user_id);
                return Relationship.friends;
            } else {
                repo.addFollower(user_id, other_id);
                return Relationship.heFollower;
            }
        }
    }

    @Override
    public Relationship remove(Long user_id, Long other_id) {
        if (user_id.equals(other_id)) return Relationship.youOwn;

        if (repo.findFriend(user_id, other_id) != null) {
            repo.removeFriend(user_id, other_id);
            repo.removeFriend(other_id, user_id);
            repo.addFollower(user_id, other_id);
            return Relationship.heFollower;
        } else {
            if (repo.findFollower(other_id, user_id) != null) {
                repo.removeFollower(other_id, user_id);
                return Relationship.none;
            } else {
                return Relationship.none;
            }
        }
    }

    @Override
    public Relationship find(Long user_id, Long other_id) {
        if (user_id.equals(other_id)) return Relationship.youOwn;

        if (repo.findFriend(user_id, other_id) != null) {
            return Relationship.friends;
        } else if (repo.findFollower(user_id, other_id) != null) {
            return Relationship.heFollower;
        } else if (repo.findFollower(other_id, user_id) != null) {
            return Relationship.youFollowed;
        } else {
            return Relationship.none;
        }
    }
}
