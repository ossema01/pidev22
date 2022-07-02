package tn.esprit.wellbeing.modules.collaborations.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.collaborations.models.Offre;
import tn.esprit.wellbeing.modules.collaborations.models.Reservation;
import tn.esprit.wellbeing.modules.collaborations.repositories.OffreRepository;

@Service
public class OffreServiceImpl implements IOffreService {

	@Autowired
	OffreRepository offreRepo;

	private static final Logger l = LogManager.getLogger(OffreServiceImpl.class);

	@Override
	public List<Offre> retrieveAllOffers() {
		List<Offre> offers = null;
		try {

			l.info("In Method retrieveAllOffers");
			offers = (List<Offre>) offreRepo.findAll();
			l.debug("connexion à la DB Ok :");
			for (Offre offer : offers) {
				l.debug("offers :" + offer.getTitle());
			}
			l.info("Out of Method retrieveAllOffers with Success : " + offers.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAllOffers with Errors : " + e);
		}

		return offers;
	}

	@Override
	public Offre addOffre(Offre offre) {
		Offre offer_saved = null;

		try {
			l.info("In Method addOffer");
			offer_saved = offreRepo.save(offre);
			l.info("Out of Method addOffer with Success : " + offer_saved.getId());

		} catch (Exception e) {
			l.error("Out of Method addOffer with Errors : " + e);
		}

		return offer_saved;
	}

	@Override
	public void deleteOffre(Long id) {
		try {
			l.info("In Method deleteOffer");
			offreRepo.deleteById(id);
			l.info("Out of Method deleteOffer with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteOffer with Errors : " + e);
		}

	}

	@Override
	public Offre updateOffre(Offre offer) {
		Offre offerUpdated = null;

		try {
			l.info("In Method offerUpdated");
			offerUpdated = offreRepo.save(offer);
			l.info("Out of Method offerUpdated with Success : " + offerUpdated.getId());

		} catch (Exception e) {
			l.error("Out of Method offerUpdated with Errors : " + e);
		}

		return offerUpdated;
	}

	@Override
	public Offre retrieveOffre(Long id) {
		Optional<Offre> offer = null;
		try {
			l.info("In Method retrieveOffer");
			offer = offreRepo.findById(id);
			if (offer.isPresent()) {
				l.info("Out of Method retrieveOffer with Success : " + offer.get().getTitle());
				return offer.get();
			}
		} catch (Exception e) {
			l.error("Out of Method retrieveOffer with Errors : " + e);
		}

		return null;
	}

}
