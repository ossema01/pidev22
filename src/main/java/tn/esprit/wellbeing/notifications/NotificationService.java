package tn.esprit.wellbeing.notifications;

public interface NotificationService {

	/**
	 * 
	 * This methods checks for every param type to determine
	 * the message to be sent, the reciever and the HasNotification
	 * object
	 * 
	 * If a param is missing, a notificationFactory should be implemented
	 * 
	 * @param params
	 */
	void sendNotification(Object... params);

}
