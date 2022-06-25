package tn.esprit.wellbeing.modules.notifications.provider;

import tn.esprit.wellbeing.modules.notifications.HasNotifications;
import tn.esprit.wellbeing.modules.notifications.NotificationProvider;
import tn.esprit.wellbeing.modules.notifications.data.Notification;

public class DefaultNotificationProvider implements NotificationProvider {

	public static final DefaultNotificationProvider INSTANCE = new DefaultNotificationProvider();

	private DefaultNotificationProvider() {

	}

	@Override
	public NotificationProvider getInstance() {
		return INSTANCE;
	}

	@Override
	public Class<? extends HasNotifications> getHasNotificationsSubject() {
		return null;
	}

	@Override
	public Notification getNotification(HasNotifications entity) {
		return null;
	}

	@Override
	public Notification getNotification(HasNotifications entity, Long userId, String message) {
		return null;
	}

	@Override
	public Notification getNotification(HasNotifications entity, Long userId) {
		return null;
	}

	@Override
	public Notification getNotification(Long userId, String message) {
		return null;
	}

	@Override
	public Notification getNotification(HasNotifications entity, String message) {
		return null;
	}

}
