package ru.itlab.sem.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Image photo;
    @NotEmpty(message = "Name can not be empty")
    @Size(max = 30, message = "You can enter max 30 letters. Use your shortest name")
    private String name;
    @NotEmpty(message = "Surname can not be empty")
    @Size(max = 30, message = "You can enter max 30 letters. Use your shortest surname")
    private String surname;
    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userData_id", referencedColumnName = "id")
    private UserData userData;
    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<User> friends;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;
}
