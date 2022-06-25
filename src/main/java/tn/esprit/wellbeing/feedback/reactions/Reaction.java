package tn.esprit.wellbeing.feedback.reactions;

import javax.persistence.Entity;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class Reaction extends SuperEntity {

	private ReactionType reactionType;

	public ReactionType getReactionType() {
		return reactionType;
	}

	public void setReactionType(ReactionType reactionType) {
		this.reactionType = reactionType;
	}

}
