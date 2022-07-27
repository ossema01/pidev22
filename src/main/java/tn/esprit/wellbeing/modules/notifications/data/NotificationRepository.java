package tn.esprit.wellbeing.modules.notifications.data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

	List<Notification> findByToUser(String toUser);

	List<Notification> findByToUserAndStatus(String toUser, NotificationStatus status);

	@Query("select n from Notification n where n.sendAt >= :from and n.sendAt < :to ")
	List<Notification> findScheduled(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

	@Query("select n from Notification n where n.createdAt < :from and n.status = 'SENT' and n.sendAt = null and n.type = 'DEFAULT'")
	List<Notification> findNotReadNotifications(@Param("from") LocalDateTime from);
}
