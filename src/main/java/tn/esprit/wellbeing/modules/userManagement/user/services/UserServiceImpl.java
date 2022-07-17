package tn.esprit.wellbeing.modules.userManagement.user.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.wellbeing.modules.userManagement.role.entity.Role;
import tn.esprit.wellbeing.modules.userManagement.role.repository.RoleRepository;
import tn.esprit.wellbeing.modules.userManagement.user.entity.ResetPasswordToken;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.entity.VerificationToken;
import tn.esprit.wellbeing.modules.userManagement.user.model.UserModel;
import tn.esprit.wellbeing.modules.userManagement.user.repository.UserRepository;
import tn.esprit.wellbeing.modules.userManagement.user.services.resetPassword.ResetPasswordService;
import tn.esprit.wellbeing.modules.userManagement.user.services.verificationToken.VerificationTokenService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Transactional
@Slf4j
@Service(value = "userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private ResetPasswordService resetPasswordTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRolesToUser(String userName, String[] roles) {
        log.info("Adding role {} to user {}", roles, userName);
        User user = userRepository.findByUsername(userName);
        for (String roleName : roles) {
            Role role = new Role();
            role.setRole(roleName);
            role.setUser(user);
            roleRepository.save(role);
        }
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public User updateUserProfile(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User registerUser(UserModel model) {
        if (model.getPassword().equals(model.getConfirmPassword())) {
            User user = new User();
            user.setEmail(model.getEmail());
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setUsername(model.getUsername());
            user.setPassword(passwordEncoder(model.getPassword()));
            user.setMonthlyActive(0);
            userRepository.save(user);
            List<Role> userRoles = new ArrayList<>();
            for (String roleName : model.getRoles()) {
                Role role = new Role();
                role.setRole(roleName);
                role.setUser(user);
                userRoles.add(role);
            }
            roleRepository.saveAll(userRoles);
            return user;
        }
        return null;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        var verificationToken = new VerificationToken(user, token);
        verificationTokenService.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if (verificationToken == null || !verificationToken.getToken().equals(token)) {
            return "Invalid Token";
        }

        var user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            verificationTokenService.deleteById(verificationToken.getId());
            return "Expired Token";
        }
        verificationTokenService.deleteById(verificationToken.getId());
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public void saveResetPasswordTokenForUser(String token, User user) {
        var resetPasswordToken = new ResetPasswordToken(user, token);
        resetPasswordTokenService.save(resetPasswordToken);
    }

    @Override
    public String validateResetPasswordToken(String token) {
        ResetPasswordToken resetPasswordToken = resetPasswordTokenService.findByToken(token);
        if (resetPasswordToken == null || !resetPasswordToken.getToken().equals(token)) {
            return "Invalid Token";
        }

        var user = resetPasswordToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (resetPasswordToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            resetPasswordTokenService.deleteById(resetPasswordToken.getId());
            return "Expired Token";
        }
        resetPasswordTokenService.deleteById(resetPasswordToken.getId());
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        var verificationToken = verificationTokenService.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenService.save(verificationToken);
        return verificationToken;
    }

    @Override
    public User findByToken(String token) {
        return resetPasswordTokenService.findByToken(token).getUser();
    }

    @Override
    public String passwordEncoder(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Boolean matchesPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return user;
    }
}
