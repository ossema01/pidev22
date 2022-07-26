package tn.esprit.wellbeing.modules.evaluation.services;

import java.util.List;

import tn.esprit.wellbeing.modules.evaluation.models.Badge;

public interface IBadgeService {
	
	List<Badge> retrieveAllBadges(); 
	Badge addBadge(Badge badge);
	void deleteBadge(Long id);
	Badge updateBadge(Badge badge);
	Badge retrieveBadge(Long id);


	List<Badge> retrieveAllUnconfirmedBadges(); 
	List<Badge> retrieveAllConfirmedBadges(); 
	Badge retrieveUnconfirmedBadge(Long id);
	List<Badge> getUserBadges(String username); 

	
	
}
