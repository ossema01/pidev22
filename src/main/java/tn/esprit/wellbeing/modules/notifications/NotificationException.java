package tn.esprit.wellbeing.modules.notifications;

import javax.mail.MessagingException;

public class NotificationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotificationException(String message) {
		super(message);
	}

	public NotificationException(MessagingException e) {
		super(e);
	}
}
