package tn.esprit.wellbeing.modules.forum.service;

import tn.esprit.wellbeing.modules.forum.models.Recommendation;

public interface RecommandationService  extends AbstractForumObjectService<Recommendation> {
	
	void approveRecommandation(Long recommandationId);
	void rejectRecommandation(Long recommandationId);

}
