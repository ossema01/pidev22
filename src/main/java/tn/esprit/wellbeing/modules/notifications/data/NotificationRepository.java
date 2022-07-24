package tn.esprit.wellbeing.modules.notifications.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

	List<Notification> findByToUser(String toUser);

	List<Notification> findByToUserAndStatus(String toUser, NotificationStatus status);

}
