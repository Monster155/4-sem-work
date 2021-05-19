package ru.itlab.sem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.sem.repositories.RelationshipRepo;
import ru.itlab.sem.services.RelationshipService;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    private RelationshipRepo repo;

    //                     _________________________________
    // TODO make better - | User 1 | User 2 | Relationship |
    //                    ---------------------------------

    @Override
    public Relationship add(Long user_id, Long follower_id) {
        if (repo.findFollower(user_id, follower_id) != null) {
            return Relationship.otherFollower;
        } else {
            if (repo.findFollower(follower_id, user_id) != null) {
                repo.removeFollower(follower_id, user_id);
                repo.addFriend(user_id, follower_id);
                repo.addFriend(follower_id, user_id);
                return Relationship.friends;
            } else {
                repo.addFollower(user_id, follower_id);
                return Relationship.otherFollower;
            }
        }
    }

    @Override
    public Relationship remove(Long user_id, Long follower_id) {
        if (user_id.equals(follower_id)) return Relationship.youOwn;

        if (repo.findFriend(user_id, follower_id) != null) {
            repo.removeFriend(user_id, follower_id);
            repo.removeFriend(follower_id, user_id);
            repo.addFollower(user_id, follower_id);
            return Relationship.otherFollower;
        } else {
            if (repo.findFollower(follower_id, user_id) != null) {
                repo.removeFollower(follower_id, user_id);
                return Relationship.none;
            } else {
                return Relationship.none;
            }
        }
    }

    @Override
    public Relationship find(Long user_id, Long follower_id) {
        if (user_id.equals(follower_id)) return Relationship.youOwn;

        if (repo.findFriend(user_id, follower_id) != null) {
            return Relationship.friends;
        } else if (repo.findFollower(user_id, follower_id) != null) {
            return Relationship.otherFollower;
        } else if (repo.findFollower(follower_id, user_id) != null) {
            return Relationship.userFollower;
        } else {
            return Relationship.none;
        }
    }

    @Override
    public Relationship change(Long user_id, Long follower_id) {
        if (user_id.equals(follower_id)) return Relationship.youOwn;

        Relationship rel = find(user_id, follower_id);
        switch (rel) {
            case none:
            case otherFollower:
                return add(follower_id, user_id);
            case userFollower:
                return remove(follower_id, user_id);
            case friends:
                return remove(user_id, follower_id);
        }
        return Relationship.none;
    }
}
