package tn.esprit.wellbeing.modules.collaborations.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.collaborations.models.Collaboration;
import tn.esprit.wellbeing.modules.collaborations.repositories.CollaborationRepository;

@Service
public class CollaborationServiceImpl implements ICollaborationService {

	@Autowired
	CollaborationRepository collaborationRepo;
	
	private static final Logger l = LogManager.getLogger(CollaborationServiceImpl.class);


	@Override
	public List<Collaboration> retrieveAllCollaborations() {
		List<Collaboration> collaborations = null;
		try {

			l.info("In Method retrieveAllCollaborations");
			collaborations = (List<Collaboration>) collaborationRepo.findAll();
			l.debug("connexion à la DB Ok :");
			for (Collaboration collaboration : collaborations) {
				l.debug("collaborations :" + collaboration.getPartnerName());
			}
			l.info("Out of Method retrieveAllCollaborations with Success : " + collaborations.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAllCollaborations with Errors : " + e);
		}

		return collaborations;
	}

	@Override
	public Collaboration addCollaboration(Collaboration collab) {
		Collaboration collab_saved = null;

		try {
			l.info("In Method addCollaboration");
			collab_saved = collaborationRepo.save(collab);
			l.info("Out of Method addCollaboration with Success : " + collab_saved.getPartnerName());

		} catch (Exception e) {
			l.error("Out of Method addCollaboration with Errors : " + e);
		}

		return collab_saved;
	}

	@Override
	public void deleteCollaboration(Long id) {
		try {
			l.info("In Method deleteCollaboration");
			collaborationRepo.deleteById(id);
			l.info("Out of Method deleteCollaboration with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteCollaboration with Errors : " + e);
		}

	}

	@Override
	public Collaboration updateCollaboration(Collaboration collab) {
		Collaboration collaborationUpdated = null;

		try {
			l.info("In Method collaborationeUpdated");
			collaborationUpdated = collaborationRepo.save(collab);
			l.info("Out of Method collaborationUpdated with Success : " + collaborationUpdated.getPartnerName());

		} catch (Exception e) {
			l.error("Out of Method collaborationUpdated with Errors : " + e);
		}

		return collaborationUpdated;
	}

	@Override
	public Collaboration retrieveCollaboration(Long id) {
		Optional<Collaboration> collab = null;
		try {
			l.info("In Method retrieveCollaboration");
			collab = collaborationRepo.findById(id);
			if (collab.isPresent()) {
				l.info("Out of Method retrieveCollaboration with Success : " + collab.get().getPartnerName());
				return collab.get();
			}
		} catch (Exception e) {
			l.error("Out of Method retrieveCollaboration with Errors : " + e);
		}

		return null;
	}

}
