package tn.esprit.wellbeing.modules.evaluation.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.evaluation.models.UnconfirmedBadge;
import tn.esprit.wellbeing.modules.evaluation.repositories.UnconfirmedBadgeRepository;

@Service
public class UnconfirmedBadgeServiceImpl implements IUnconfirmedBadgeService {

	@Autowired
	UnconfirmedBadgeRepository unconfirmedBadgeRepo;

	private static final Logger l = LogManager.getLogger(UnconfirmedBadgeServiceImpl.class);

	@Override
	public List<UnconfirmedBadge> retrieveAllUnconfirmedBadges() {
		List<UnconfirmedBadge> unconfirmedBadges = null;
		try {

			l.info("In Method retrieveAllUnconfirmedBadges");
			unconfirmedBadges = (List<UnconfirmedBadge>) unconfirmedBadgeRepo.findAll();
			l.debug("connexion à la DB Ok :");
			for (UnconfirmedBadge unconfirmedBadge : unconfirmedBadges) {
				l.debug("unconfirmedBadges :" + unconfirmedBadge.getTitle());
			}
			l.info("Out of Method retrieveAllUnconfirmedBadges with Success : " + unconfirmedBadges.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAllUnconfirmedBadges with Errors : " + e);
		}

		return unconfirmedBadges;
	}

	@Override
	public UnconfirmedBadge addUnconfirmedBadge(UnconfirmedBadge unconfirmedBadge) {
		UnconfirmedBadge unconfirmedBadge_saved = null;

		try {
			l.info("In Method addUnconfirmedBadge");
			unconfirmedBadge_saved = unconfirmedBadgeRepo.save(unconfirmedBadge);
			l.info("Out of Method addUnconfirmedBadge with Success : " + unconfirmedBadge_saved.getTitle());

		} catch (Exception e) {
			l.error("Out of Method addUnconfirmedBadge with Errors : " + e);
		}

		return unconfirmedBadge_saved;
	}

	@Override
	public void deleteUnconfirmedBadge(Long id) {
		try {
			l.info("In Method deleteUnconfirmedBadge");
			unconfirmedBadgeRepo.deleteById(id);
			l.info("Out of Method deleteUnconfirmedBadge with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteUnconfirmedBadge with Errors : " + e);
		}

	}

	@Override
	public UnconfirmedBadge updateUnconfirmedBadge(UnconfirmedBadge UnconfirmedBadge) {
		UnconfirmedBadge unconfirmedBadgeUpdated = null;

		try {
			l.info("In Method updateUnconfirmedBadge");
			unconfirmedBadgeUpdated = unconfirmedBadgeRepo.save(UnconfirmedBadge);
			l.info("Out of Method updateUnconfirmedBadge with Success : " + unconfirmedBadgeUpdated.getTitle());

		} catch (Exception e) {
			l.error("Out of Method updateUnconfirmedBadge with Errors : " + e);
		}

		return unconfirmedBadgeUpdated;
	}

	@Override
	public UnconfirmedBadge retrieveUnconfirmedBadge(Long id) {
		Optional<UnconfirmedBadge> unconfirmedBadge = null;
		try {
			l.info("In Method retrieveUnconfirmedBadge");
			unconfirmedBadge = unconfirmedBadgeRepo.findById(id);
			if (unconfirmedBadge.isPresent()) {
				l.info("Out of Method retrieveUnconfirmedBadge with Success : " + unconfirmedBadge.get().getTitle());
				return unconfirmedBadge.get();
			}
		} catch (Exception e) {
			l.error("Out of Method retrieveUnconfirmedBadge with Errors : " + e);
		}

		return null;
	}

}
