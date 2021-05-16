package ru.itlab.sem.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserRegConDTO {

    @NotEmpty(message = "Name can not be empty")
    @Size(max = 30, message = "You can enter max 30 letters. Use your shortest name")
    private String name;

    @NotEmpty(message = "Surname can not be empty")
    @Size(max = 30, message = "You can enter max 30 letters. Use your shortest surname")
    private String surname;

    @NotEmpty(message = "Location can not be empty")
    @Size(max = 50, message = "You can enter max 50 letters. Use your shortest location name")
    private String location;
}
