package ru.itlab.sem.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "relationships")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    private User user1;

    //    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    private User user2;

    private Relations relation;

    public enum Relations{
        FRIENDS,
        FIRST_FOLLOWER_OF_SECOND,
        SECOND_FOLLOWER_OF_FIRST,
        NONE
    }
}
