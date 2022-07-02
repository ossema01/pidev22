package tn.esprit.wellbeing.modules.evaluation.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.evaluation.models.Badge;
import tn.esprit.wellbeing.modules.evaluation.repositories.BadgeRepository;

@Service
public class BadgeServiceImpl implements IBadgeService {

	@Autowired
	BadgeRepository badgeRepo;

	private static final Logger l = LogManager.getLogger(BadgeServiceImpl.class);

	@Override
	public List<Badge> retrieveAllBadges() {
		List<Badge> badges = null;
		try {

			l.info("In Method retrieveAllBadges");
			badges = (List<Badge>) badgeRepo.findAll();
			l.debug("connexion à la DB Ok :");
			for (Badge badge : badges) {
				l.debug("badges :" + badge.getTitle());
			}
			l.info("Out of Method retrieveAllBadges with Success : " + badges.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAllBadges with Errors : " + e);
		}

		return badges;
	}

	@Override
	public Badge addBadge(Badge badge) {
		Badge badge_saved = null;

		try {
			l.info("In Method addBadge");
			badge_saved = badgeRepo.save(badge);
			l.info("Out of Method addBadge with Success : " + badge_saved.getTitle());

		} catch (Exception e) {
			l.error("Out of Method addBadge with Errors : " + e);
		}

		return badge_saved;
	}

	@Override
	public void deleteBadge(Long id) {
		try {
			l.info("In Method deleteBadge");
			badgeRepo.deleteById(id);
			l.info("Out of Method deleteBadge with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteBadge with Errors : " + e);
		}

	}

	@Override
	public Badge updateBadge(Badge badge) {
		Badge badgeUpdated = null;

		try {
			l.info("In Method updateBadge");
			badgeUpdated = badgeRepo.save(badge);
			l.info("Out of Method updateBadge with Success : " + badgeUpdated.getTitle());

		} catch (Exception e) {
			l.error("Out of Method updateBadge with Errors : " + e);
		}

		return badgeUpdated;
	}

	@Override
	public Badge retrieveBadge(Long id) {
		Optional<Badge> badge = null;
		try {
			l.info("In Method retrieveBadge");
			badge = badgeRepo.findById(id);
			if (badge.isPresent()) {
				l.info("Out of Method retrieveBadge with Success : " + badge.get().getTitle());
				return badge.get();
			}
		} catch (Exception e) {
			l.error("Out of Method retrieveBadge with Errors : " + e);
		}

		return null;
	}

}
