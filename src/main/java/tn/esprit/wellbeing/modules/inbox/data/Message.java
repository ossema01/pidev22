package tn.esprit.wellbeing.modules.inbox.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class Message extends SuperEntity {

	private String body;

	private String toUser;

	private MessageStatus status = MessageStatus.Created;

	@Transient
	private List<String> suggestedReplies = new ArrayList<>();

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

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public void addSuggestedReply(String reply) {
		this.suggestedReplies.add(reply);
	}

	public List<String> getSuggestedReplies() {
		return this.suggestedReplies;
	}

	@PostLoad
	public void computeSuggestions() {
		if (this.body.toLowerCase().contains("hello") || this.body.toLowerCase().contains("hi ")) {
			this.suggestedReplies.add("Hello !");
			this.suggestedReplies.add("Hi");
			this.suggestedReplies.add("How are you?");
		} else if (this.body.toLowerCase().contains("how are you")) {
			this.suggestedReplies.add("I'm find and you ?");
			this.suggestedReplies.add("I'm ok");
			this.suggestedReplies.add("Great! How about you ?");
		} else if (this.body.toLowerCase().endsWith("?")) {
			this.suggestedReplies.add("No way!");
			this.suggestedReplies.add("Sure!");
			this.suggestedReplies.add("Why not.");
		} else if (this.body.toLowerCase().contains("do you")) {
			this.suggestedReplies.add("Sounds good.");
			this.suggestedReplies.add("No sorry");
			this.suggestedReplies.add("Sure");
		}
	}
}
