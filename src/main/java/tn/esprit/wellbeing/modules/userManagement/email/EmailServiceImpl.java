package tn.esprit.wellbeing.modules.userManagement.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            messageHelper.setFrom("noreply@pidev.com");
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            log.warn("Email could not be sent to user '{}': {}", toEmail, ex.getMessage());
        }
    }
}
