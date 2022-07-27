package tn.esprit.wellbeing.modules.userManagement.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.wellbeing.modules.userManagement.role.entity.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"authorities"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private int pointsNumber;

    private int monthlyActive;

    private LocalDate lastLogin;

    @Column(length = 60)
    private String password;

    private boolean enabled = false;

    private boolean blocked = false;

    private boolean archived = false;

    @ElementCollection
    private List<String> interests;

    private String phoneNumber;

    @JsonIgnoreProperties(value = {"user"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.ALL})
    private List<Role> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().collect(Collectors.toList());
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
}
