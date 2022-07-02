package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import tn.esprit.wellbeing.modules.occurences.models.Invitation;
import tn.esprit.wellbeing.modules.occurences.repositories.InvitationRepository;

@Service
public class InvitationServiceImpl implements IInvitationService {
	@Autowired
	InvitationRepository invitationRepository;

	private static final Logger l = LogManager.getLogger(InvitationServiceImpl.class);

	@Override
	public List<Invitation> retrieveAllInvitations() {
		List<Invitation> invitations = null;
		try {

			l.info("In Method retrieveAllInvitations :");
			invitations = (List<Invitation>) invitationRepository.findAll();
			l.debug("connexion à la DB Ok :");
			for (Invitation invitation : invitations) {
				l.debug("invitation :" + invitation);
			}
			l.info("Out of Method retrieveAllInvitations with Success" + invitations.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAllInvitations with Errors : " + e);
		}

		return invitations;
	}

	@Override
	public Invitation createInvitation(Invitation invitation) {
		Invitation invitation_saved = null;

		try {
			l.info("In Method addInvitation :");
			invitation_saved = invitationRepository.save(invitation);
			l.info("Out of Method addInvitation with Success" + invitation_saved);

		} catch (Exception e) {
			l.error("Out of Method addInvitation with Errors : " + e);
		}

		return invitation_saved;

	}

	@Override
	public void deleteInvitation(String id) {
		try {
			l.info("In Method deleteInvitation :");
			invitationRepository.deleteById(id);
			l.info("Out of Method deleteInvitation with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteInvitation with Errors : " + e);
		}
	}

	@Override
	public Invitation updateInvitation(Invitation invitation) {

		Invitation invitationUpdated = null;

		try {
			l.info("In Method invitationUpdated :");
			invitationUpdated = invitationRepository.save(invitation);
			l.info("Out of Method invitationUpdated with Success" + invitationUpdated);

		} catch (Exception e) {
			l.error("Out of Method invitationUpdated with Errors : " + e);
		}

		return invitationUpdated;
	}

	@Override
	public Invitation retrieveInvitation(String id) {
		Optional<Invitation> invitation = null;
		try {
			l.info("In Method retrieveInvitation :");
			invitation = invitationRepository.findById(id);
			if (invitation.isPresent()) {
				l.info("Out of Method retrieveInvitation with Success" + invitation.get());
				return invitation.get();
			}

		} catch (Exception e) {
			l.error("Out of Method retrieveInvitation with Errors : " + e);
		}
		return null;

	}


}
