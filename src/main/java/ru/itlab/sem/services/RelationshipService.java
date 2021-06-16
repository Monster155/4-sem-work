package ru.itlab.sem.services;

import ru.itlab.sem.models.Relationship;
import ru.itlab.sem.models.Relationship.Relations;

public interface RelationshipService {

    Relationship change(Long user_id, Long other_id);

    Relationship change(Long user_id, Long other_id, Relations relations);

    Relationship find(Long user_id, Long other_id);

//    Relationship add(Long user_id, Long other_id);
//    Relationship remove(Long user_id, Long other_id);
//    Relationship find(Long user_id, Long other_id);
//    Relationship change(Long user_id, Long other_id);
}
