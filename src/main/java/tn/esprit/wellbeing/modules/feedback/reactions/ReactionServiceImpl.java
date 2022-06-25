package tn.esprit.wellbeing.modules.feedback.reactions;

import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService {

	@Override
	public void addReaction(HasReactions entity, ReactionType reactionType) {

		Reaction reaction = new Reaction();
		reaction.setReactionType(reactionType);

		entity.addReaction(reaction);

	}

}
