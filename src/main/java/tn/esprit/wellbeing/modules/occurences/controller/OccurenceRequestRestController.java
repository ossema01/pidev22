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

import tn.esprit.wellbeing.modules.occurences.models.OccurenceRequest;
import tn.esprit.wellbeing.modules.occurences.services.IOccurenceRequestService;

@RestController
public class OccurenceRequestRestController {

	@Autowired
	IOccurenceRequestService occurenceRequestService;

	// http://localhost:8090/pidev/retrieve-all-occurenceRequests
	@GetMapping("/retrieve-all-occurenceRequests")
	public List<OccurenceRequest> retrieveAllOccurenceRequests() {
		List<OccurenceRequest> list = occurenceRequestService.retrieveAllOccurenceRequests();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-occurenceRequest/{occurenceRequest-id}
	@GetMapping("/retrieve-occurenceRequest/{occurenceRequest-id}")
	public OccurenceRequest retrieveOccurenceRequest(@PathVariable("occurenceRequest-id") Long occurenceRequestId) {
		return occurenceRequestService.retrieveOccurenceRequest(occurenceRequestId);
	}

	// http://localhost:8090/pidev/create-occurenceRequest
	@PostMapping("/create-occurenceRequest")
	public OccurenceRequest createOccurenceRequest(@RequestBody OccurenceRequest occRequest) {
		OccurenceRequest occurenceRequest = occurenceRequestService.createOccurenceRequest(occRequest);
		return occurenceRequest;
	}

	// http://localhost:8090/pidev/remove-occurenceRequest/{occurenceRequest-id}
	@DeleteMapping("/remove-occurenceRequest/{occurenceRequest-id}")
	public void removeOccurenceRequest(@PathVariable("occurenceRequest-id") Long occurenceRequestId) {
		occurenceRequestService.deleteOccurenceRequest(occurenceRequestId);
	}

	// http://localhost:8090/pidev/modify-occurenceRequest
	@PutMapping("/modify-occurenceRequest")
	public OccurenceRequest updateOccurenceRequest(@RequestBody OccurenceRequest occRequest) {
		return occurenceRequestService.updateOccurenceRequest(occRequest);
	}

}
