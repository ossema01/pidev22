package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;

import tn.esprit.wellbeing.modules.occurences.models.OccurenceRequest;

public interface IOccurenceRequestService {
	List<OccurenceRequest> retrieveAllOccurenceRequests(); 
	OccurenceRequest createOccurenceRequest(OccurenceRequest occurenceRequest);
	void deleteOccurenceRequest(String id);
	OccurenceRequest updateOccurenceRequest(OccurenceRequest en);
	OccurenceRequest retrieveOccurenceRequest(String id);
}
