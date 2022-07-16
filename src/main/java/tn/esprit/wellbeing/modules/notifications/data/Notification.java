package tn.esprit.wellbeing.modules.notifications.data;

import javax.persistence.Entity;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class Notification extends SuperEntity {

	private String message;

	private Long userId;

	private NotificationStatus status;
	
	private NotificationType type = NotificationType.DEFAULT;

	public String getMessage() {
		return message;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}
	
	

}
