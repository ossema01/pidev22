package tn.esprit.wellbeing.modules.userManagement.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.wellbeing.modules.userManagement.email.EmailService;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.entity.VerificationToken;
import tn.esprit.wellbeing.modules.userManagement.user.event.RegistrationCompleteEvent;
import tn.esprit.wellbeing.modules.userManagement.user.model.UserModel;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final EmailService emailService;

    private final ApplicationEventPublisher publisher;

    @GetMapping("/users/searchAll")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.created(null).body(userService.saveUser(user));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserModel model, final HttpServletRequest request) {
        var user = userService.registerUser(model);
        publisher.publishEvent(new RegistrationCompleteEvent(user, getApplicationUrl(request)));
        return ResponseEntity.created(null).body(user);
    }

    @GetMapping("/verifyRegistration")
    public ResponseEntity<String> verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return ResponseEntity.ok().body("User Verified Successfully");
        }
        return ResponseEntity.ok().body("Bad User");
    }

    @GetMapping("/resendVerifyToken")
    public ResponseEntity<String> resendVerificationToken(@RequestParam("token") String oldToken, @RequestParam("email") String email, HttpServletRequest request) {
        User userdata = userService.findByEmail(email);
        log.info("user is {}", userdata.isEnabled());
        if (!userdata.isEnabled()) {
            var verificationToken = userService.generateNewVerificationToken(oldToken);
            var user = verificationToken.getUser();
            resendVerificationTokenMail(email, getApplicationUrl(request), verificationToken);
            return ResponseEntity.ok().body("Verification Link Sent");
        }
        return ResponseEntity.ok().body("User already verified");
    }

    private void resendVerificationTokenMail(String email, String applicationUrl, VerificationToken verificationToken) {
        //send mail to user
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();
        String subject = "Verification email";
        String body = url;
        emailService.sendEmail(email, subject, body);
        //sendVerificationEmail
        log.info("Click the link to verify your account: {}", url);
    }

    private String getApplicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
