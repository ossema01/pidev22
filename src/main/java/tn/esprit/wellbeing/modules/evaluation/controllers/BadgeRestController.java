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
import tn.esprit.wellbeing.modules.evaluation.services.IBadgeService;

@RestController
public class BadgeRestController {

	@Autowired
	IBadgeService badgeService;

	// http://localhost:8090/pidev/retrieve-all-badges
	@GetMapping("/retrieve-all-badges")
	public List<Badge> retrieveAllBadges() {
		List<Badge> list = badgeService.retrieveAllBadges();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-badge/{badge-id}
	@GetMapping("/retrieve-badge/{badge-id}")
	public Badge retrieveBadge(@PathVariable("badge-id") Long badgeId) {
		return badgeService.retrieveBadge(badgeId);
	}

	// http://localhost:8090/pidev/add-badge
	@PostMapping("/add-badge")
	public Badge addBadge(@RequestBody Badge badge) {
		Badge addedBadge = badgeService.addBadge(badge);
		return addedBadge;
	}

	// http://localhost:8090/pidev/remove-badge/{badge-id}
	@DeleteMapping("/remove-badge/{badge-id}")
	public void removeBadge(@PathVariable("badge-id") Long badgeId) {
		badgeService.deleteBadge(badgeId);
	}

	// http://localhost:8090/pidev/modify-badge
	@PutMapping("/modify-badge")
	public Badge updateBadge(@RequestBody Badge badge) {
		return badgeService.updateBadge(badge);
	}

}
