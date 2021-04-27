package ru.itlab.sem.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "images")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private byte[] photo;
}
