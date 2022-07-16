package tn.esprit.wellbeing.modules.notifications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.notifications.HasNotifications;
import tn.esprit.wellbeing.modules.notifications.NotificationException;
import tn.esprit.wellbeing.modules.notifications.NotificationProvider;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationRepository;
import tn.esprit.wellbeing.modules.notifications.data.NotificationStatus;
import tn.esprit.wellbeing.modules.notifications.data.NotificationType;
import tn.esprit.wellbeing.modules.notifications.provider.NotificationProviderFactory;
import tn.esprit.wellbeing.modules.userManagement.user.repository.UserRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private JavaMailSender mailer;
	@Value("${spring.mail.username}")
	private String sender;
	@Autowired
	private NotificationRepository repo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public void sendNotification(Object... params) {

		HasNotifications hasNotif = null;
		Long userId = null;
		String message = null;
		NotificationType type = NotificationType.DEFAULT;
		for (Object param : params) {
			if (param instanceof HasNotifications) {
				hasNotif = (HasNotifications) param;
			} else if (param instanceof Long) {
				userId = (Long) param;
			} else if (param instanceof String) {
				message = (String) param;
			} else if (param instanceof NotificationType) {
				type = (NotificationType) param;
			}
		}

		Notification notif = createNotificationUsingProvider(hasNotif, userId, message, type);
		if (notif == null) {
			throw new NotificationException("Coudn't create notification using provider. Please check your paremeters");
		}
		if (notif.getType().equals(NotificationType.MAIL)) {
			sendMail(notif);
		} else {
			defaultSendNotif(notif);
		}

	}

	private void defaultSendNotif(Notification notif) {

	}

	private Notification createNotificationUsingProvider(HasNotifications hasNotif, Long userId, String message,
			NotificationType type) {
		Notification notif;
		NotificationProvider provider;
		if (hasNotif != null && NotificationProviderFactory.hasProvider(hasNotif)) {
			provider = NotificationProviderFactory.getNotificationProvider(hasNotif);
			if (userId != null && message != null && type != null) {
				notif = provider.getNotification(hasNotif, userId, message, type);
			} else if (userId != null && message != null) {
				notif = provider.getNotification(hasNotif, userId, message);
			} else if (userId == null) {
				notif = provider.getNotification(hasNotif, message);
			} else {
				notif = provider.getNotification(hasNotif, userId);
			}
		} else

		{
			provider = NotificationProviderFactory.getDefaultProvider();
			notif = provider.getNotification(hasNotif, userId, message, type);
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

	private void sendMail(Notification notif) {
//		MimeMessage mimeMessage = mailer.createMimeMessage();
//		MimeMessageHelper mimeMessageHelper;
//		mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//		mimeMessageHelper.setFrom(sender);
//		mimeMessageHelper.setTo(userRepo.findById(notif.getCreatedBy()).getEmail());
//		mimeMessageHelper.setText(notif.getMessage());
//		mimeMessageHelper.setSubject(notif.getSubject());
//		mailer.send(mimeMessage);
	}

}
