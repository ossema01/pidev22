package tn.esprit.wellbeing.modules.userManagement.user.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import tn.esprit.wellbeing.modules.userManagement.email.EmailService;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.event.RegistrationCompleteEvent;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create the verification token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);

        //send mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        String subject = "Verification email";
        String body = url;
        emailService.sendEmail(user.getEmail(), subject, body);
        //sendVerificationEmail
        log.info("Click the link to verify your account: {}", url);
    }
}
