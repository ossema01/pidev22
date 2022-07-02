package tn.esprit.wellbeing.modules.evaluation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.evaluation.models.Badge;
import tn.esprit.wellbeing.modules.evaluation.models.UnconfirmedBadge;
import tn.esprit.wellbeing.modules.evaluation.services.IUnconfirmedBadgeService;

@RestController
public class UnconfirmedBadgeRestController {
	
	@Autowired
	IUnconfirmedBadgeService unconfirmedBadgeService;

	// http://localhost:8090/pidev/retrieve-all-unconfirmedBadges
	@GetMapping("/retrieve-all-unconfirmedBadges")
	public List<UnconfirmedBadge> retrieveAllUnconfirmedBadges() {
		List<UnconfirmedBadge> list = unconfirmedBadgeService.retrieveAllUnconfirmedBadges();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-unconfirmedBadge/{unconf-id}
	@GetMapping("/retrieve-unconfirmedBadge/{unconf-id}")
	public UnconfirmedBadge retrieveUnconfirmedBadge(@PathVariable("unconf-id") Long unconfBadgeId) {
		return unconfirmedBadgeService.retrieveUnconfirmedBadge(unconfBadgeId);
	}

	// http://localhost:8090/pidev/add-unconfirmedBadge
	@PostMapping("/add-unconfirmedBadge")
	public UnconfirmedBadge addUnconfirmedBadge(@RequestBody UnconfirmedBadge unconfBadgeId) {
		UnconfirmedBadge addedUnconfBadge = unconfirmedBadgeService.addUnconfirmedBadge(unconfBadgeId);
		return addedUnconfBadge;
	}

	// http://localhost:8090/pidev/remove-unconfirmedBadge/{unconf-id}
	@DeleteMapping("/remove-unconfirmedBadge/{unconf-id}")
	public void removeUnconfirmedBadge(@PathVariable("unconf-id") Long unconfBadgeId) {
		unconfirmedBadgeService.deleteUnconfirmedBadge(unconfBadgeId);
	}

	// http://localhost:8090/pidev/modify-unconfirmedBadge
	@PutMapping("/modify-unconfirmedBadge")
	public UnconfirmedBadge updateUnconfirmedBadge(@RequestBody UnconfirmedBadge unconfBadge) {
		return unconfirmedBadgeService.updateUnconfirmedBadge(unconfBadge);
	}

	
	

}
