package tn.esprit.wellbeing.modules.userManagement.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.event.RegistrationCompleteEvent;
import tn.esprit.wellbeing.modules.userManagement.user.event.ResetPasswordCompleteEvent;
import tn.esprit.wellbeing.modules.userManagement.user.event.UpdateProfileCompleteEvent;
import tn.esprit.wellbeing.modules.userManagement.user.model.Credentials;
import tn.esprit.wellbeing.modules.userManagement.user.model.UserModel;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;
import tn.esprit.wellbeing.modules.userManagement.user.services.resetPassword.ResetPasswordService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final ResetPasswordService resetPasswordService;

    private final ApplicationEventPublisher publisher;

    @GetMapping("/users/searchAll")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel model, final HttpServletRequest request) {
        if (userService.findByEmail(model.getEmail()) == null) {
            var user = userService.registerUser(model);
            if (!model.getPassword().equals(model.getConfirmPassword())) {
                return ResponseEntity.badRequest().body("Password should be equal confirmPassword.");
            }
            publisher.publishEvent(new RegistrationCompleteEvent(user, getApplicationUrl(request)));
            return ResponseEntity.created(null).body("User was created successfully.");
        }
        return ResponseEntity.ok().body("User already exist.");
    }

    @GetMapping("/verifyRegistration")
    public ResponseEntity<String> verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return ResponseEntity.ok().body("User Verified Successfully");
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/verifyUpdateProfile")
    public ResponseEntity<String> verifyUpdateProfile(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return ResponseEntity.ok().body("User Verified Successfully");
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/resendVerifyToken")
    public ResponseEntity<String> resendVerificationToken(@RequestParam("token") String oldToken, @RequestBody Credentials credentials, HttpServletRequest request) {
        User userdata = userService.findByEmail(credentials.getEmail());
        log.info("user is {}", userdata.isEnabled());
        if (userdata != null && !userdata.isEnabled()) {
            var verificationToken = userService.generateNewVerificationToken(oldToken);
            var user = verificationToken.getUser();
            publisher.publishEvent(new RegistrationCompleteEvent(userdata, getApplicationUrl(request)));
            return ResponseEntity.ok().body("Verification Link Sent");
        }
        return ResponseEntity.ok().body("User already verified");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> sendResetUserPasswordMail(@RequestBody Credentials credentials, final HttpServletRequest request) {
        User user = userService.findByEmail(credentials.getEmail());
        if (user == null) {
            return ResponseEntity.ok().body("User email " + credentials.getEmail() + " not found.");
        }
        var resetPasswordToken = resetPasswordService.findByUserId(user.getId());

        if (resetPasswordToken == null) {
            var userEnabled = user.isEnabled();

            if (userEnabled) {
                publisher.publishEvent(new ResetPasswordCompleteEvent(user, getApplicationUrl(request)));
                return ResponseEntity.ok().body("Email was sent successfully.");
            }
            return ResponseEntity.ok().body("confirm your account at: " + credentials.getEmail());
        }
        return ResponseEntity.ok().body("Reset password email was already sent to " + credentials.getEmail());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotUserPassword(@RequestParam("token") String token, @RequestBody Credentials credentials) {
        User user = userService.findByToken(token);
        var verificationCredentials = credentials.getPassword().equals(credentials.getConfirmPassword());
        String encodedPassword = userService.passwordEncoder(credentials.getPassword());

        if (!verificationCredentials) {
            return ResponseEntity.badRequest().body("User password does not equal confirmPassword.");
        }
        if (!user.getPassword().equals(encodedPassword)) {
            var resetPasswordToken = userService.validateResetPasswordToken(token);

            if (!resetPasswordToken.equalsIgnoreCase("valid")) {
                return ResponseEntity.ok().body(resetPasswordToken);
            }
            return ResponseEntity.ok().body("User password was updated successfully.");
        }
        return ResponseEntity.ok().body("New password should not be equal to the last used password");
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/update-profile")
    public ResponseEntity<String> updateUserProfile(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.findByEmail(userModel.getEmail());
        if (user == null) {
            return ResponseEntity.badRequest().body("User with email " + userModel.getEmail() + " not found.");
        }

        if (user != null && user.isEnabled()) {
            if (userModel.getOldPassword() == null) {
                return ResponseEntity.badRequest().body("Password" +
                        " should not be null.");
            }

            if (!userService.matchesPassword(userModel.getOldPassword(), user.getPassword())) {
                return ResponseEntity.badRequest().body("Entered wrong old password.");
            }

            if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
                return ResponseEntity.badRequest().body("Password should be equal confirmPassword.");
            }

            if (userService.matchesPassword(userModel.getPassword(), user.getPassword()) || userService.matchesPassword(userModel.getConfirmPassword(), user.getPassword())) {
                return ResponseEntity.badRequest().body("New password should not equal old password.");
            }

            if (!userService.matchesPassword(userModel.getOldPassword(), user.getPassword())) {
                if (userModel.getPassword().equals(userModel.getConfirmPassword())) {
                    if (!userService.matchesPassword(userModel.getPassword(), user.getPassword())) {
                        return ResponseEntity.badRequest().body("Password should not equal pervious password.");
                    }
                    user.setPassword(userService.passwordEncoder(userModel.getPassword()));
                }
            }

            if (userModel.getNewEmail() != null) {
                user.setEmail(userModel.getNewEmail());
            }

            userService.updateUserProfile(user);

            if (userModel.getNewEmail() != null) {
                publisher.publishEvent(new UpdateProfileCompleteEvent(user, getApplicationUrl(request)));
            }
            return ResponseEntity.ok().body("Verification email was sent, please check your email inbox.");
        }

        return ResponseEntity.badRequest().body("User not verified, please check your email inbox.");
    }

    @PreAuthorize("hasRole('HR')")
    @PostMapping("/hr/block/{username}")
    public ResponseEntity<String> blockUser(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.blockUser(username));
    }

    @PreAuthorize("hasRole('HR')")
    @PostMapping("/hr/bulk/block")
    public ResponseEntity<String> bulkBlockUser(@RequestBody String[] usernames) {
        List<String> wrongUsernames = new ArrayList<>();
        for (String username : usernames) {
            String result = userService.blockUser(username);
            if (result == "User not found, please verify the entered username.") {
                wrongUsernames.add(username);
            }
        }
        log.info("wrong size: {}, usernames length: {}", wrongUsernames.size(), usernames.length);
        if (wrongUsernames.size() == usernames.length) {
            return ResponseEntity.ok().body("Failed to block the given users, please verify the entered username.");
        }

        if (wrongUsernames.size() > 0) {
            return ResponseEntity.ok().body("Users Blocked Successfully, except those: " + String.join(", ", wrongUsernames));
        }
        return ResponseEntity.ok().body("Users Blocked Successfully");
    }

    @PreAuthorize("hasRole('HR')")
    @GetMapping("/hr/monthly-active")
    public ResponseEntity<User[]> monthlyActiveUsers() {
        User[] users = userService.getMonthlyActiveUsers();
        return ResponseEntity.ok().body(users);
    }

    @PreAuthorize("hasRole('HR')")
    @DeleteMapping("/hr/user/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().body("User was Deleted");
    }
    @PreAuthorize("hasRole('HR')")
    @DeleteMapping("/hr/bulk/user/")
    public ResponseEntity<String> deleteUsers(@RequestBody String[] usernames) {
        userService.deleteUsers(usernames);
        return ResponseEntity.ok().body("User was Deleted");
    }

    private String getApplicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
