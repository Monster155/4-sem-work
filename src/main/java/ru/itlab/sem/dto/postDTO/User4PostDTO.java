package ru.itlab.sem.dto.postDTO;

import lombok.*;
import ru.itlab.sem.dto.ImageDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class User4PostDTO {
    private long id;
    private ImageDTO photo;
    private String name;
    private String surname;
    private String fullname;
    private String nickname;
}
