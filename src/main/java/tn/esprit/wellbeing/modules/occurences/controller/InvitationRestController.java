package tn.esprit.wellbeing.modules.occurences.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.occurences.models.Invitation;
import tn.esprit.wellbeing.modules.occurences.services.IInvitationService;

//InvitationRestController
	@RestController // = @Controller + @ResponseBody 
	//@Scope("session") 
	//singleton c'est par défaut 
	//prototype 
	//request
public class InvitationRestController {
		// Couplage Faible 
		@Autowired 
		IInvitationService invitationService; 
		// URL : http://localhost:????/????/retrieve-all-Invitations
		@GetMapping("/retrieve-all-Invitations")
		public List<Invitation> retrieveAllInvitations() {
			List<Invitation> list = invitationService.retrieveAllInvitations();
			return list;
		}
	 
		// http://localhost:????/timesheet-devops/retrieve-invitation/{invitation-id}
		@GetMapping("/retrieve-invitation/{employee-id}")
		public Invitation retrieveInvitation(@PathVariable("employee-id") Long invitationId) {
			return invitationService.retrieveInvitation(invitationId);
		}

		// Ajouter invitation : http://localhost:????/timesheet-devops/add-invitation 
		@PostMapping("/add-invitation")
		public Invitation addInvitation(@RequestBody Invitation inv) {
			Invitation invitation = invitationService.createInvitation(inv); 
			return invitation;
		}

		
		// Supprimer invitation : 
		// http://localhost:????/timesheet-devops/remove-invitation/{invitation-id}
		@DeleteMapping("/remove-invitation/{invitation-id}") 
		public void removeInvitation(@PathVariable("invitation-id") Long invitationId) { 
			invitationService.deleteInvitation(invitationId);
		} 
		// Modifier invitation 
		// http://localhost:????/timesheet-devops/modify-invitation 
		@PutMapping("/modify-invitation") 
		public Invitation updateInvitation(@RequestBody Invitation invitation) {
			return invitationService.updateInvitation(invitation);
		}
}
