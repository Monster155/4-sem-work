package ru.itlab.sem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ImageDTO {
    private long id;
    @ToString.Exclude
    private String photo;
}
