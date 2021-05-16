package ru.itlab.sem.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PostDTO {
    private long id;
    private UserProfileDTO owner;
    private String text;
    private List<ImageDTO> images;
    private Timestamp timestamp;
}
