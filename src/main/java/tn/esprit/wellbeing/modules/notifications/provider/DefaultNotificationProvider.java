package tn.esprit.wellbeing.modules.notifications.provider;

import tn.esprit.wellbeing.modules.notifications.HasNotifications;
import tn.esprit.wellbeing.modules.notifications.NotificationException;
import tn.esprit.wellbeing.modules.notifications.NotificationProvider;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationType;

public class DefaultNotificationProvider implements NotificationProvider {

	public static final DefaultNotificationProvider INSTANCE = new DefaultNotificationProvider();

	private DefaultNotificationProvider() {

	}

	@Override
	public NotificationProvider getInstance() {
		return INSTANCE;
	}

	@Override
	public Class<? extends HasNotifications> getSubject() {
		return HasNotifications.class;
	}

	@Override
	@Deprecated
	public Notification getNotification(HasNotifications entity) {
		throw new NotificationException(
				"Please use getNotification(HasNotifications entity, Long userId, String message)");
	}

	@Override
	public Notification getNotification(HasNotifications entity, Long userId, String message) {
		if (userId == null || message == null) {
			throw new NotificationException(String.format("%s is null", userId == null ? "userId" : "message"));
		}
		Notification notif = new Notification();
		notif.setUserId(userId);
		notif.setMessage(message);
		return notif;
	}

	@Override
	@Deprecated
	public Notification getNotification(HasNotifications entity, Long userId) {
		throw new NotificationException(
				"Please use getNotification(HasNotifications entity, Long userId, String message)");
	}

	@Override
	public Notification getNotification(Long userId, String message) {
		Notification notif = new Notification();
		notif.setUserId(userId);
		notif.setMessage(message);
		return notif;
	}

	@Override
	@Deprecated
	public Notification getNotification(HasNotifications entity, String message) {
		throw new NotificationException(
				"Please use getNotification(HasNotifications entity, Long userId, String message)");
	}

	@Override
	public Notification getNotification(HasNotifications entity, Long userId, String message, NotificationType type) {
		Notification notif = new Notification();
		notif.setUserId(userId);
		notif.setMessage(message);
		notif.setType(type);
		return notif;
	}

	@Override
	public Notification getNotification(HasNotifications entity, String message, NotificationType type) {
		throw new NotificationException(
				"Please use getNotification(HasNotifications entity, Long userId, String message, NotificationType type)");
	}

	@Override
	public Notification getNotification(Long userId, String message, NotificationType type) {
		Notification notif = new Notification();
		notif.setUserId(userId);
		notif.setMessage(message);
		notif.setType(type);
		return notif;
	}

}
