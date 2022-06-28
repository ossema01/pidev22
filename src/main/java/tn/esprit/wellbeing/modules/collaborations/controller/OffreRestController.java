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

import tn.esprit.wellbeing.modules.collaborations.models.Offre;
import tn.esprit.wellbeing.modules.collaborations.services.IOffreService;

@RestController
public class OffreRestController {

	@Autowired
	IOffreService offreService;

	// http://localhost:8090/pidev/retrieve-all-offers
	@GetMapping("/retrieve-all-offers")
	public List<Offre> retrieveAllOffres() {
		List<Offre> list = offreService.retrieveAllOffers();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-offer/{offer-id}
	@GetMapping("/retrieve-offer/{offre-id}")
	public Offre retrieveOffre(@PathVariable("offre-id") Long offerId) {
		return offreService.retrieveOffre(offerId);
	}

	// http://localhost:8090/pidev/add-offer
	@PostMapping("/add-offer")
	public Offre addOffre(@RequestBody Offre offer) {
		Offre offre = offreService.addOffre(offer);
		return offre;
	}

	// http://localhost:8090/pidev/remove-offer/{offer-id}
	@DeleteMapping("/remove-offer/{offre-id}")
	public void removeOffre(@PathVariable("offre-id") Long offerId) {
		offreService.deleteOffre(offerId);
	}

	// http://localhost:8090/pidev/modify-offer
	@PutMapping("/modify-offer")
	public Offre updateOffre(@RequestBody Offre offer) {
		return offreService.updateOffre(offer);
	}

}
