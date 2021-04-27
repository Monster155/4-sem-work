package ru.itlab.sem.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "posts")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;
    @Column(columnDefinition = "text")
    private String text;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Image> images;
    @CreationTimestamp
    private Timestamp timestamp;
}
