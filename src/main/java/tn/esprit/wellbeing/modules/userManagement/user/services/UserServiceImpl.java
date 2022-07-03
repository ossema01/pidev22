package tn.esprit.wellbeing.modules.userManagement.user.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import tn.esprit.wellbeing.modules.userManagement.user.repository.ResetPasswordTokenRepository;
import tn.esprit.wellbeing.modules.userManagement.user.repository.UserRepository;
import tn.esprit.wellbeing.modules.userManagement.user.repository.VerificationTokenRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role {} to user {}", roleName, userName);
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
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
            user.setRole("USER");
            user.setPassword(passwordEncoder.encode(model.getPassword()));
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        var verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null || !verificationToken.getToken().equals(token)) {
            return "Invalid Token";
        }

        var user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "Expired Token";
        }
        verificationTokenRepository.deleteById(verificationToken.getId());
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public void saveResetPasswordTokenForUser(String token, User user) {
        var resetPasswordToken = new ResetPasswordToken(user, token);
        resetPasswordTokenRepository.save(resetPasswordToken);
    }

    @Override
    public String validateResetPasswordToken(String token) {
        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);
        if (resetPasswordToken == null || !resetPasswordToken.getToken().equals(token)) {
            return "Invalid Token";
        }

        var user = resetPasswordToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (resetPasswordToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            resetPasswordTokenRepository.delete(resetPasswordToken);
            return "Expired Token";
        }
        resetPasswordTokenRepository.deleteById(resetPasswordToken.getId());
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        var verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }
}
