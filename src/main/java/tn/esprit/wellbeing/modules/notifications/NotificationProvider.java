package tn.esprit.wellbeing.modules.notifications;

import tn.esprit.wellbeing.modules.notifications.data.Notification;

public interface NotificationProvider {

	NotificationProvider getInstance();

	Class<? extends HasNotifications> getSubject();

	Notification getNotification(HasNotifications entity);

	Notification getNotification(HasNotifications entity, Long userId, String message);

	Notification getNotification(HasNotifications entity, String message);

	Notification getNotification(HasNotifications entity, Long userId);

	Notification getNotification(Long userId, String message);
	
}
