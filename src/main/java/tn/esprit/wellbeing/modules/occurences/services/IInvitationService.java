package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;

import tn.esprit.wellbeing.modules.occurences.models.Invitation;


public interface IInvitationService {
	List<Invitation> retrieveAllInvitations(); 
	Invitation createInvitation(Invitation invitation);
	void deleteInvitation(String id);
	Invitation updateInvitation(Invitation en);
	Invitation retrieveInvitation(String id);
}
