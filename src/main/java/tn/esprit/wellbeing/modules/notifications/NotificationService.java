package tn.esprit.wellbeing.modules.notifications;

import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {

    /**
     * This methods checks for every param type to determine the message to be sent,
     * the reciever , the HasNotification object and the notification type (
     * DEFAULT, MAIL, SYSTEM_MESSAGE )
     * <p>
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

    Notification fireEvent(Long id);

    void forceChangeStatus(Notification notification, NotificationStatus toStatus);

    void sendNotification(String toUser, String text);

    void sendNotification(String toUser, String text, LocalDateTime when);

    void sendSystemNotification(String enail, Notification notif);

}
