package ru.itlab.sem.dto.userDTO;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserLoginDTO {
    @NotEmpty(message = "E-mail can not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password can not be empty")
    private String password;
}
