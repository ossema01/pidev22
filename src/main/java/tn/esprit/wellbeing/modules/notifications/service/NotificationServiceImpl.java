package tn.esprit.wellbeing.modules.notifications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.notifications.HasNotifications;
import tn.esprit.wellbeing.modules.notifications.NotificationException;
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
		if (notif == null) {
			throw new NotificationException("Coudn't create notification using provider. Please check your paremeters");
		}
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
		changeStatus(notif);
		return notif;
	}

	@Override
	public List<Notification> findNotificationByUserId(Long userId) {
		return repo.findByUserId(userId);
	}

	@Override
	public List<Notification> findNotificationByUserIdAndStatus(Long userId, NotificationStatus status) {
		return repo.findByUserIdAndStatus(userId, status);
	}

	@Override
	public void changeStatus(Notification notification) {
		NotificationStatus status = notification.getStatus();
		if (status == null) {
			notification.setStatus(NotificationStatus.CREATED);
		} else if (NotificationStatus.CREATED.equals(status)) {
			notification.setStatus(NotificationStatus.SENT);
		} else if (NotificationStatus.SENT.equals(status)) {
			notification.setStatus(NotificationStatus.READ);
		} else if (NotificationStatus.READ.equals(status)) {
			notification.setStatus(NotificationStatus.DELETED);
		}
		repo.save(notification);
	}

	@Override
	public void forceChangeStatus(Notification notification, NotificationStatus toStatus) {
		notification.setStatus(toStatus);
		repo.save(notification);
	}

}
