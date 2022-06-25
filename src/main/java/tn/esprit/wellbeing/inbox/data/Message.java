package tn.esprit.wellbeing.inbox.data;

import javax.persistence.Entity;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class Message extends SuperEntity {

	private String body;

	private Long userId;
	
	private MessageStatus status;

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
