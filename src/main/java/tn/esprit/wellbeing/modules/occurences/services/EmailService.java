package tn.esprit.wellbeing.modules.occurences.services;

import tn.esprit.wellbeing.modules.occurences.models.EmailDetails;

public interface EmailService {
	  // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
 
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
