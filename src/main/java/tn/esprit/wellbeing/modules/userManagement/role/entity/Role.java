package tn.esprit.wellbeing.modules.userManagement.role.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String role;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Override
    public String getAuthority() {
        return role;
    }
}
