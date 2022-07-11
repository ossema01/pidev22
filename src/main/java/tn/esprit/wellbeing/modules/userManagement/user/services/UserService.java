package tn.esprit.wellbeing.modules.userManagement.user.services;

import tn.esprit.wellbeing.modules.userManagement.role.entity.Role;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.entity.VerificationToken;
import tn.esprit.wellbeing.modules.userManagement.user.model.UserModel;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    User getUser(String userName);

    User updateUserProfile(User user);

    List<User> getUsers();

    User findByEmail(String email);

    User registerUser(UserModel model);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    void saveResetPasswordTokenForUser(String token, User user);

    String validateResetPasswordToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findByToken(String token);

    String passwordEncoder(String password);

    Boolean matchesPassword(String password, String encodedPassword);
}
