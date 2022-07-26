package tn.esprit.wellbeing.modules.collaborations.services;

import java.util.List;

import tn.esprit.wellbeing.modules.collaborations.models.Offre;

public interface IOffreService {
	
	List<Offre> retrieveAllOffers(); 
	Offre addOffre(Offre offre);
	void deleteOffre(Long id);
	Offre updateOffre(Offre offre);
	Offre retrieveOffre(Long id);
	void addRating(Long offerId, float rating);

}
