package tn.esprit.wellbeing.modules.userManagement.user.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Transactional
@Slf4j
@Service
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
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        user.getAuthorities().clear();
        resetPasswordTokenService.deleteByUserId(user.getId());
        verificationTokenService.deleteByUserId(user.getId());
        userRepository.deleteByUsername(username);
    }

    @Override
    public void deleteUsers(String[] userNamesList) {
        for (String username : userNamesList) {
            userRepository.deleteByUsername(username);
        }
    }

    @Override
    public User updateUserProfile(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void updateMonthlyActive(String username) {
        User user = userRepository.findByUsername(username);
        if (user.getLastLogin() == null) {
            user.setMonthlyActive(1);
        } else {
            if (user.getLastLogin().compareTo(LocalDate.now()) < 0) {
                log.info(" last login date: {} ", user.getLastLogin().compareTo(LocalDate.now()));
                user.setMonthlyActive(user.getMonthlyActive() + 1);
            }
        }
        user.setLastLogin(LocalDate.now());
        userRepository.save(user);
    }

    @Override
    public User[] getMonthlyActiveUsers() {
        return userRepository.findAllByOrderByMonthlyActiveDesc();
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
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String blockUser(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return "User not found, please verify the entered username.";
        }
        user.setBlocked(true);
        return "User Blocked Successfully";
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
            if (model.getPointsNumber() != null) {
                user.setPointsNumber(model.getPointsNumber());
            } else {
                user.setPointsNumber(0);
            }
            if (model.getPhoneNumber() != null) {
                user.setPhoneNumber(model.getPhoneNumber());
            }
            if (model.getInterests() != null) {
                user.setInterests(model.getInterests());
            }
            user.setMonthlyActive(0);
            userRepository.save(user);
            if (model.getRoles() != null) {
                List<Role> userRoles = new ArrayList<>();
                for (String roleName : model.getRoles()) {
                    Role role = new Role();
                    role.setRole(roleName);
                    role.setUser(user);
                    userRoles.add(role);
                }
                roleRepository.saveAll(userRoles);
            }
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

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
