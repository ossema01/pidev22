package tn.esprit.wellbeing.modules.userManagement.user.entity;

import lombok.*;
import tn.esprit.wellbeing.modules.userManagement.role.entity.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;
    
    private int pointsNumber;

    @Column(length = 60)
    private String password;

    private String role;

    private boolean enabled = false;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
