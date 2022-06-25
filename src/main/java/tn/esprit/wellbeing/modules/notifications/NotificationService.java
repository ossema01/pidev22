package tn.esprit.wellbeing.modules.notifications;

import java.util.List;

import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationStatus;

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
	
	
	List<Notification> findNotificationByUserId(Long userId);
	
	List<Notification> findNotificationByUserIdAndStatus(Long userId, NotificationStatus status);

	void changeStatus(Notification notification);
	
	void forceChangeStatus(Notification notification, NotificationStatus toStatus);


}
