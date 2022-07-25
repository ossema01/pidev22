package tn.esprit.wellbeing.modules.forum;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.comments.HasComments;
import tn.esprit.wellbeing.modules.feedback.reactions.HasReactions;
import tn.esprit.wellbeing.modules.feedback.reactions.Reaction;
import tn.esprit.wellbeing.modules.forum.models.SurveyContent;
import tn.esprit.wellbeing.modules.notifications.HasNotifications;

@MappedSuperclass
public class AbstractForumObject<T> extends SuperEntity implements HasNotifications, HasComments, HasReactions {

	public static final String ANONYMOUS_CREATOR = "\"This post is created anonymously\"";

	private String title;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reaction> reactions = new ArrayList<>();

	@Any(metaColumn = @Column(name = "content_type"), fetch = FetchType.EAGER)
	@AnyMetaDef(idType = "integer", metaType = "string", metaValues = {
			@MetaValue(value = "String", targetEntity = String.class),
			@MetaValue(value = "Survey", targetEntity = SurveyContent.class) })
	@JoinColumn(name = "content_id")
	private T content;

	private String topic;

	private boolean anonymous;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
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
		// if (InterfaceToSecurity.getCurrentUser().isAdmin()){
		// return super.getCreatedBy();
		// }
		return anonymous ? ANONYMOUS_CREATOR : super.getCreatedBy();
	}

	@Override
	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public void addComment(Comment comment) {
		comments.add(comment);
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
