package tn.esprit.wellbeing.modules.notifications;

import java.util.List;

import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationStatus;

public interface NotificationService {

	/**
	 * 
	 * This methods checks for every param type to determine the message to be sent,
	 * the reciever , the HasNotification object and the notification type (
	 * DEFAULT, MAIL, SYSTEM_MESSAGE )
	 * 
	 * If a param is missing, a notificationFactory should be implemented
	 * 
	 * @param params
	 */
	void sendNotification(Object... params);

	void sendNotification(Notification notif);

	List<Notification> findNotificationByUserId(String userId);

	List<Notification> getCurrentUserNotifications();

	List<Notification> findNotificationByUserIdAndStatus(String userId, NotificationStatus status);

	void changeStatus(Notification notification);

	void forceChangeStatus(Notification notification, NotificationStatus toStatus);

}
