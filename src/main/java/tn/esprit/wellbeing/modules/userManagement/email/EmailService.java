package tn.esprit.wellbeing.modules.userManagement.email;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
}
