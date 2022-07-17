package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;

import tn.esprit.wellbeing.modules.occurences.models.AbstractOccurence;


public interface IAbstractOccurenceService {
	List<AbstractOccurence> retrieveAllAbstractOccurences(); 
	AbstractOccurence createAbstractOccurence(AbstractOccurence abstractOccurence);
	void deleteAbstractOccurence(Long id);
	AbstractOccurence updateAbstractOccurencet(AbstractOccurence en);
	AbstractOccurence retrieveAbstractOccurence(Long id);
}
