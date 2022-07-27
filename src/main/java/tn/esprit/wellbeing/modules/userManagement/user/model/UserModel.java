package tn.esprit.wellbeing.modules.userManagement.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.wellbeing.modules.userManagement.role.entity.Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String firstName;
    
    private String lastName;

    private String email;

    private Integer pointsNumber;

    private String newEmail;

    private String username;

    private String password;

    private String oldPassword;

    private String confirmPassword;

    private List<String> roles;
}
