package tn.esprit.wellbeing.feedback.comments;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tn.esprit.wellbeing.feedback.reactions.HasReactions;
import tn.esprit.wellbeing.feedback.reactions.Reaction;
import tn.esprit.wellbeing.models.SuperEntity;

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
