package tn.esprit.wellbeing.modules.notifications.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

	List<Notification> findByUserId(Long userId);

	List<Notification> findByUserIdAndStatus(Long userId, NotificationStatus status);

}