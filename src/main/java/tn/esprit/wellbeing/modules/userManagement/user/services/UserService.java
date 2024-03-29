package tn.esprit.wellbeing.modules.userManagement.user.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tn.esprit.wellbeing.modules.userManagement.role.entity.Role;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.entity.VerificationToken;
import tn.esprit.wellbeing.modules.userManagement.user.model.UserModel;

import java.util.List;

public interface UserService extends UserDetailsService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRolesToUser(String userName, String[] roles);

    User getUser(String userName);

    void deleteUser(String userName);

    void deleteUsers(String[] userNamesList);

    User updateUserProfile(User user);

    void updateMonthlyActive(String username);

    User[] getMonthlyActiveUsers();

    List<User> getUsers();

    User findByEmail(String email);

    User findByUsername(String username);

    String blockUser(String username);

    User registerUser(UserModel model);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    void saveResetPasswordTokenForUser(String token, User user);

    String validateResetPasswordToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findByToken(String token);

    String passwordEncoder(String password);

    Boolean matchesPassword(String password, String encodedPassword);

    User getCurrentUser();

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
