package tn.esprit.wellbeing.modules.feedback.comments;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.feedback.reactions.HasReactions;
import tn.esprit.wellbeing.modules.feedback.reactions.Reaction;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationType;
import tn.esprit.wellbeing.modules.notifications.provider.NotificationProviderFactory;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Entity
public class Comment extends SuperEntity implements HasReactions {

	private String body;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> replies = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reaction> reactions = new ArrayList<>();

	public List<Comment> getReplies() {
		return replies;
	}

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}

	public void addReply(Comment reply) {
		this.replies.add(reply);
	}

	public boolean removeReply(Comment reply) {
		return this.replies.remove(reply);
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public List<Reaction> getReactions() {
		return reactions;
	}

	@Override
	public void addReaction(Reaction reaction) {
		reactions.add(reaction);
	}

	@Override
	public boolean removeReaction(Reaction reaction) {
		return reactions.remove(reaction);
	}

}
