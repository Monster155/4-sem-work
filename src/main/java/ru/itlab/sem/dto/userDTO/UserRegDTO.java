package ru.itlab.sem.dto.userDTO;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserRegDTO {
    @NotEmpty(message = "Nickname can not be empty")
    @Size(max = 30, message = "You can enter max 30 letters. Use your shortest nickname")
    private String nickname;

    @NotEmpty(message = "E-mail can not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password can not be empty")
    @Size(min = 8, max = 30, message = "Password must contain from 8 to 30 characters")
    private String password;

//    private Role role;
//    private Properties properties;
}
