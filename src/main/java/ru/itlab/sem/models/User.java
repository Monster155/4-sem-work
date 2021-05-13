package ru.itlab.sem.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    private Image photo;

    //    @NotEmpty(message = "Name can not be empty")
//    @Size(max = 30, message = "You can enter max 30 letters. Use your shortest name")
    private String name;

    //    @NotEmpty(message = "Surname can not be empty")
//    @Size(max = 30, message = "You can enter max 30 letters. Use your shortest surname")
    private String surname;

    //    @NotEmpty(message = "Nickname can not be empty")
//    @Size(max = 30, message = "You can enter max 30 letters. Use your shortest nickname")
    private String nickname;

    //    @NotEmpty(message = "E-mail can not be empty")
//    @Email
    private String email;

    //    @NotEmpty(message = "Password can not be empty")
//    @Size(min = 8, max = 30, message = "Password must contain from 8 to 30 characters")
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<User> friends;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

//    @ManyToMany()
//    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
        return Collections.singleton(new Role(Role.Names.USER));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
