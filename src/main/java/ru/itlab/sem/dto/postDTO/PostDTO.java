package ru.itlab.sem.dto.postDTO;

import lombok.*;
import ru.itlab.sem.dto.ImageDTO;

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
    private User4PostDTO owner;
    private String text;
    private List<ImageDTO> images;
    private Timestamp timestamp;
}
