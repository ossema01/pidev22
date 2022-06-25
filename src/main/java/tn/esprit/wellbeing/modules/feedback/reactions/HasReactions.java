package tn.esprit.wellbeing.modules.feedback.reactions;

import java.util.List;

public interface HasReactions {

	List<Reaction> getReactions();

	void addReaction(Reaction reaction);

	boolean removeReaction(Reaction reaction);

}
