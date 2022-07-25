package tn.esprit.wellbeing.modules.notifications.data;

import javax.persistence.Entity;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class Notification extends SuperEntity {

	private String message;

	private String toUser;

	private NotificationStatus status = NotificationStatus.CREATED;
	
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

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}
	
	

}
