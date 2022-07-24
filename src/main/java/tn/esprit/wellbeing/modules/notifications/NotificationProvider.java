package tn.esprit.wellbeing.modules.notifications;

import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationType;

public interface NotificationProvider {

	NotificationProvider getInstance();

	Class<? extends HasNotifications> getSubject();

	Notification getNotification(HasNotifications entity);

	Notification getNotification(HasNotifications entity, String username, String message);

	Notification getNotification(HasNotifications entity, String username, String message, NotificationType type);

	Notification getNotification(HasNotifications entity, String message);

	Notification getNotification(HasNotifications entity, String message, NotificationType type);

	Notification getNotification(HasNotifications entity, Long username);

	Notification getNotification(String username, String message);

	Notification getNotification(String username, String message, NotificationType type);

}
