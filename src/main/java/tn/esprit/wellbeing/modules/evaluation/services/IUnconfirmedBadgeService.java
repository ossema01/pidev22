package tn.esprit.wellbeing.modules.evaluation.services;

import java.util.List;

import tn.esprit.wellbeing.modules.evaluation.models.Badge;
import tn.esprit.wellbeing.modules.evaluation.models.UnconfirmedBadge;

public interface IUnconfirmedBadgeService {
	List<UnconfirmedBadge> retrieveAllUnconfirmedBadges(); 
	UnconfirmedBadge addUnconfirmedBadge(UnconfirmedBadge unconfirmedBadge);
	void deleteUnconfirmedBadge(Long id);
	UnconfirmedBadge updateUnconfirmedBadge(UnconfirmedBadge unconfirmedBadge);
	UnconfirmedBadge retrieveUnconfirmedBadge(Long id);

}
