package tn.esprit.wellbeing.modules.collaborations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.wellbeing.modules.collaborations.models.Collaboration;
import tn.esprit.wellbeing.modules.collaborations.services.ICollaborationService;

@RestController
public class CollaborationRestController {

	@Autowired
	ICollaborationService collaborationService;

	// http://localhost:8090/pidev/retrieve-all-collaborations
	@GetMapping("/retrieve-all-collaborations")
	public List<Collaboration> retrieveAllCollaborations() {
		List<Collaboration> list = collaborationService.retrieveAllCollaborations();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-collaboration/{collaboration-id}
	@GetMapping("/retrieve-collaboration/{collaboration-id}")
	public Collaboration retrieveCollaboration(@PathVariable("collaboration-id") Long collabId) {
		return collaborationService.retrieveCollaboration(collabId);
	}

	// http://localhost:8090/pidev/add-collaboration
	@PostMapping("/add-collaboration")
	public Collaboration addCollaboration(@RequestBody Collaboration colab) {
		Collaboration colaboration = collaborationService.addCollaboration(colab);
		return colaboration;
	}

	// http://localhost:8090/pidev/remove-collaboration/{collaboration-id}
	@DeleteMapping("/remove-collaboration/{collaboration-id}")
	public void removeCollaboration(@PathVariable("collaboration-id") Long collabId) {
		collaborationService.deleteCollaboration(collabId);
	}

	// http://localhost:8090/pidev/modify-collaboration
	@PutMapping("/modify-collaboration")
	public Collaboration updateCollaboration(@RequestBody Collaboration colab) {
		return collaborationService.updateCollaboration(colab);
	}

}
