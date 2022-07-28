package tn.esprit.wellbeing.modules.forum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.comments.HasComments;
import tn.esprit.wellbeing.modules.feedback.reactions.HasReactions;
import tn.esprit.wellbeing.modules.feedback.reactions.Reaction;
import tn.esprit.wellbeing.modules.notifications.HasNotifications;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@MappedSuperclass
public class AbstractForumObject extends SuperEntity implements HasNotifications, HasComments, HasReactions {

	public static final String ANONYMOUS_CREATOR = "\"This post is created anonymously\"";

	private String title;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reaction> reactions = new ArrayList<>();

	private String content;

	private String topic;

	private boolean anonymous;
	
	private boolean suspendedComments;
	
	public boolean isSuspendedComments() {
		return suspendedComments;
	}

	public void setSuspendedComments(boolean suspendedComments) {
		this.suspendedComments = suspendedComments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * if the consumer of this API is an administrator, so the real creator can be
	 * visible
	 **/
	@Override
	public String getCreatedBy() {
		boolean hr = WellBeingApplication.context.getBean(UserService.class).getCurrentUser()
			.getAuthorities()
			.stream()
			.map(e->e.getAuthority())
			.anyMatch(e->e.equals("ROLE_HR"));
		 if (hr){
			 return super.getCreatedBy();
		 }
		return anonymous ? ANONYMOUS_CREATOR : super.getCreatedBy();
	}

	@Override
	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public void addComment(Comment comment) {
		//comments.add(comment);
	}

	@Override
	public boolean removeComment(Comment comment) {
		return comments.remove(comment);
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
