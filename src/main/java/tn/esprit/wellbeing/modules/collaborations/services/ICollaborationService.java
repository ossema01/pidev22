package tn.esprit.wellbeing.modules.collaborations.services;

import java.util.List;

import tn.esprit.wellbeing.modules.collaborations.models.Collaboration;

public interface ICollaborationService {
	
	List<Collaboration> retrieveAllCollaborations(); 
	Collaboration addCollaboration(Collaboration collab);
	void deleteCollaboration(Long id);
	Collaboration updateCollaboration(Collaboration collab);
	Collaboration retrieveCollaboration(Long id);

}
