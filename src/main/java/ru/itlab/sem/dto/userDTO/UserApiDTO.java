package ru.itlab.sem.dto.userDTO;

import lombok.*;
import ru.itlab.sem.dto.ImageDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserApiDTO {
    private long id;
    private ImageDTO photo;
    private String name;
    private String surname;
    private String fullname;
    private String nickname;
    private String location;
    private int followersCount;
    private int friendsCount;
}
