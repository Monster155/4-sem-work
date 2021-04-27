package ru.itlab.sem.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "messages")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User sender;
    @Column(columnDefinition = "text")
    private String text;
    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private List<Image> images;
    @CreationTimestamp
    private Timestamp timestamp;
}
