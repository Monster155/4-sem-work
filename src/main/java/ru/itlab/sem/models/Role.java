package ru.itlab.sem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "roles")

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private Names name;

//    @ManyToMany(mappedBy = "roles") TODO
//    private Set<User> users;

    public Role(Names name) {
        this.name = name;
    }

    public Role(Long id, Names name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName().name();
    }

    public enum Names {
        ADMIN,
        USER,
    }

}
