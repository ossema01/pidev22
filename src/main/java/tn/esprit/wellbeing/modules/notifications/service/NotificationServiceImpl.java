package tn.esprit.wellbeing.modules.notifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.notifications.HasNotifications;
import tn.esprit.wellbeing.modules.notifications.NotificationProvider;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationRepository;
import tn.esprit.wellbeing.modules.notifications.data.NotificationStatus;
import tn.esprit.wellbeing.modules.notifications.provider.NotificationProviderFactory;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository repo;

	@Override
	public void sendNotification(Object... params) {

		HasNotifications hasNotif = null;
		Long userId = null;
		String message = null;
		for (Object param : params) {
			if (param instanceof HasNotifications) {
				hasNotif = (HasNotifications) param;
			} else if (param instanceof Long) {
				userId = (Long) param;
			} else if (param instanceof String) {
				message = (String) param;
			}
		}

		Notification notif = createNotificationUsingProvider(hasNotif, userId, message);
		repo.save(notif);
		// now send notif in MQ and save it

	}

	private Notification createNotificationUsingProvider(HasNotifications hasNotif, Long userId, String message) {
		Notification notif;
		NotificationProvider provider;
		if (hasNotif != null && NotificationProviderFactory.hasProvider(hasNotif)) {
			provider = NotificationProviderFactory.getNotificationProvider(hasNotif);
			if (userId != null && message != null) {
				notif = provider.getNotification(hasNotif, userId, message);
			} else if (userId == null) {
				notif = provider.getNotification(hasNotif, message);
			} else {
				notif = provider.getNotification(hasNotif, userId);
			}
		} else {
			provider = NotificationProviderFactory.getDefaultProvider();
			notif = provider.getNotification(hasNotif, userId, message);
		}
		notif.setStatus(NotificationStatus.Created);
		return notif;
	}

}
