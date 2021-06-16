package ru.itlab.sem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.sem.models.Relationship;
import ru.itlab.sem.models.Relationship.Relations;
import ru.itlab.sem.models.User;
import ru.itlab.sem.repositories.RelationshipRepo;
import ru.itlab.sem.services.RelationshipService;
import ru.itlab.sem.services.UserService;

import static ru.itlab.sem.models.Relationship.Relations.*;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    private RelationshipRepo repo;
    @Autowired
    private UserService userService;

    // first always has lower id
    @Override
    public Relationship change(Long user_id, Long other_id) {
        Relationship rel = find(user_id, other_id);
        switch (rel.getRelation()) {
            case FRIENDS:
                rel.setRelation(SECOND_FOLLOWER_OF_FIRST);
                repo.saveAndFlush(rel);
                return rel;
            case SECOND_FOLLOWER_OF_FIRST:
                rel.setRelation(FRIENDS);
                repo.saveAndFlush(rel);
                return rel;

            case FIRST_FOLLOWER_OF_SECOND:
                rel.setRelation(NONE);
                repo.saveAndFlush(rel);
                return rel;
            case NONE:
                rel.setRelation(FIRST_FOLLOWER_OF_SECOND);
                repo.saveAndFlush(rel);
                return rel;
        }
        return null;
    }

    @Override
    public Relationship change(Long user_id, Long other_id, Relations relations) {
        Relationship rel = find(user_id, other_id);
        rel.setRelation(relations);
        rel = repo.saveAndFlush(rel);
        return rel;
    }

    @Override
    public Relationship find(Long user_id, Long other_id) {
        Relationship rel = repo.findRelationship(user_id, other_id);
        if (rel == null) {
            rel = Relationship.builder()
                    .user1(userService.findUserById(user_id))
                    .user2(userService.findUserById(other_id))
                    .relation(NONE)
                    .build();
        } else {
            if (rel.getUser1().getId() != user_id) {
                User u = rel.getUser2();
                rel.setUser2(rel.getUser1());
                rel.setUser1(u);
                switch (rel.getRelation()) {
                    case FIRST_FOLLOWER_OF_SECOND:
                        rel.setRelation(SECOND_FOLLOWER_OF_FIRST);
                        break;
                    case SECOND_FOLLOWER_OF_FIRST:
                        rel.setRelation(FIRST_FOLLOWER_OF_SECOND);
                        break;
                }
            }
        }
        return rel;
    }

    //                     _________________________________
    // TODO make better - | User 1 | User 2 | Relationship |
    //                    ---------------------------------

    /*
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
    */
}
