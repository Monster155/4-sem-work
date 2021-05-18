package ru.itlab.sem.dto.postDTO;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PostAddDTO {
    private long id;
    private String text;
    private Timestamp timestamp;
}
