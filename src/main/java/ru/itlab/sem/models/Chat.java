package ru.itlab.sem.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "chats")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Chat name can not be empty")
    private String name;
    @NotEmpty(message = "Chat members can not be empty")
    @Size(min = 1, message = "Chat must contain at least 2 members")
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> members;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages;
}
