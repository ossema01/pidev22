package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.wellbeing.modules.occurences.models.OccurenceRequest;
import tn.esprit.wellbeing.modules.occurences.repositories.OccurenceRequestRepository;

public class OccurenceRequestServiceImpl implements IOccurenceRequestService {
	@Autowired
	OccurenceRequestRepository occurenceRequestRepository;

	private static final Logger l = LogManager.getLogger(OccurenceRequestServiceImpl.class);

	@Override
	public List<OccurenceRequest> retrieveAllOccurenceRequests() {
		List<OccurenceRequest> occurenceRequests = null;
		try {

			l.info("In Method retrieveAlloccurenceRequests :");
			occurenceRequests = (List<OccurenceRequest>) occurenceRequestRepository.findAll();
			l.debug("connexion à la DB Ok :");
			for (OccurenceRequest occurenceRequest : occurenceRequests) {
				l.debug("occurenceRequest :" + occurenceRequest);
			}
			l.info("Out of Method retrieveAlloccurenceRequests with Success" + occurenceRequests.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAlloccurenceRequests with Errors : " + e);
		}

		return occurenceRequests;
	}

	@Override
	public OccurenceRequest createOccurenceRequest(OccurenceRequest occurenceRequest) {
		OccurenceRequest occurenceRequest_saved = null;

		try {
			l.info("In Method addOccurenceRequest :");
			occurenceRequest_saved = occurenceRequestRepository.save(occurenceRequest);
			l.info("Out of Method addOccurenceRequest with Success" + occurenceRequest_saved);

		} catch (Exception e) {
			l.error("Out of Method addOccurenceRequest with Errors : " + e);
		}

		return occurenceRequest_saved;

	}

	@Override
	public void deleteOccurenceRequest(String id) {
		try {
			l.info("In Method deleteOccurenceRequest :");
			occurenceRequestRepository.deleteById(id);
			l.info("Out of Method deleteOccurenceRequest with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteOccurenceRequest with Errors : " + e);
		}
	}

	@Override
	public OccurenceRequest updateOccurenceRequest(OccurenceRequest occurenceRequest) {

		OccurenceRequest occurenceRequestUpdated = null;

		try {
			l.info("In Method occurenceRequestUpdated :");
			occurenceRequestUpdated = occurenceRequestRepository.save(occurenceRequest);
			l.info("Out of Method occurenceRequestUpdated with Success" + occurenceRequestUpdated);

		} catch (Exception e) {
			l.error("Out of Method occurenceRequestUpdated with Errors : " + e);
		}

		return occurenceRequestUpdated;
	}

	@Override
	public OccurenceRequest retrieveOccurenceRequest(String id) {
		Optional<OccurenceRequest> occurenceRequest = null;
		try {
			l.info("In Method retrieveOccurenceRequest :");
			occurenceRequest = occurenceRequestRepository.findById(id);
			if (occurenceRequest.isPresent()) {
				l.info("Out of Method retrieveOccurenceRequest with Success" + occurenceRequest.get());
				return occurenceRequest.get();
			}

		} catch (Exception e) {
			l.error("Out of Method retrieveOccurenceRequest with Errors : " + e);
		}
		return null;

	}

}
