package tn.esprit.wellbeing.modules.userManagement.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

    private String email;
    private String newEmail;
    private String userName;
    private String password;
    private String confirmPassword;
}
