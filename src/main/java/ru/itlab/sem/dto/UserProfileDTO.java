package ru.itlab.sem.dto;

import lombok.*;
import ru.itlab.sem.models.Image;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserProfileDTO {
    private long id;
    private ImageDTO photo;
    private String name;
    private String surname;
    private String fullname;
    private String nickname;
    private String location;
    private List<ImageDTO> images;
    private int followersCount;
    private int friendsCount;
}
