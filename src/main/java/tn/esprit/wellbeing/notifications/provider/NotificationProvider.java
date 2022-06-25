package tn.esprit.wellbeing.notifications.provider;

import tn.esprit.wellbeing.notifications.HasNotifications;
import tn.esprit.wellbeing.notifications.jpa.Notification;

public interface NotificationProvider {

	NotificationProvider getInstance();

	Class<? extends HasNotifications> getHasNotificationsSubject();

	Notification getNotification(HasNotifications entity);

	Notification getNotification(HasNotifications entity, Long userId, String message);

	Notification getNotification(HasNotifications entity, String message);

	Notification getNotification(HasNotifications entity, Long userId);

	Notification getNotification(Long userId, String message);

}
