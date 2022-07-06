package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.occurences.models.AbstractOccurence;
import tn.esprit.wellbeing.modules.occurences.repositories.AbstractOccurenceRepository;

@Service
public class AbstractOccurenceServiceImpl implements IAbstractOccurenceService {
	@Autowired
	AbstractOccurenceRepository abstractOccurenceRepository;

	private static final Logger l = LogManager.getLogger(AbstractOccurenceServiceImpl.class);

	@Override
	public List<AbstractOccurence> retrieveAllAbstractOccurences() {
		List<AbstractOccurence> abstractOccurences = null;
		try {

			l.info("In Method retrieveAlloccurenceRequests :");
			abstractOccurences = (List<AbstractOccurence>) abstractOccurenceRepository.findAll();
			l.debug("connexion à la DB Ok :");
			for (AbstractOccurence abstractOccurence : abstractOccurences) {
				l.debug("occurenceRequest :" + abstractOccurence);
			}
			l.info("Out of Method retrieveAlloccurenceRequests with Success" + abstractOccurences.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAlloccurenceRequests with Errors : " + e);
		}

		return abstractOccurences;
	}

	@Override
	public AbstractOccurence createAbstractOccurence(AbstractOccurence abstractOccurence) {
		AbstractOccurence abstractOccurence_saved = null;

		try {
			l.info("In Method addOccurenceRequest :");
			abstractOccurence_saved = abstractOccurenceRepository.save(abstractOccurence);
			l.info("Out of Method addOccurenceRequest with Success" + abstractOccurence_saved);

		} catch (Exception e) {
			l.error("Out of Method addOccurenceRequest with Errors : " + e);
		}

		return abstractOccurence_saved;

	}

	@Override
	public void deleteAbstractOccurence(Long id) {
		try {
			l.info("In Method deleteOccurenceRequest :");
			abstractOccurenceRepository.deleteById(id);
			l.info("Out of Method deleteOccurenceRequest with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteOccurenceRequest with Errors : " + e);
		}
	}

	@Override
	public AbstractOccurence updateAbstractOccurencet(AbstractOccurence abstractOccurence) {

		AbstractOccurence occurenceRequestUpdated = null;

		try {
			l.info("In Method updateAbstractOccurencet :");
			occurenceRequestUpdated = abstractOccurenceRepository.save(abstractOccurence);
			l.info("Out of Method updateAbstractOccurencet with Success" + occurenceRequestUpdated);

		} catch (Exception e) {
			l.error("Out of Method updateAbstractOccurencet with Errors : " + e);
		}

		return occurenceRequestUpdated;
	}
 
	@Override
	public AbstractOccurence retrieveAbstractOccurence(Long id) {
		Optional<AbstractOccurence> abstractOccurence = null;
		try {
			l.info("In Method retrieveAbstractOccurence :");
			abstractOccurence = abstractOccurenceRepository.findById(id);
			if (abstractOccurence.isPresent()) {
				l.info("Out of Method retrieveAbstractOccurence with Success" + abstractOccurence.get());
				return abstractOccurence.get();
			}

		} catch (Exception e) {
			l.error("Out of Method retrieveAbstractOccurence with Errors : " + e);
		}
		return null;

	}

}
