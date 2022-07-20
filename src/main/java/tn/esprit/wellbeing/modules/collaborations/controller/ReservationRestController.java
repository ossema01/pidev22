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
import tn.esprit.wellbeing.modules.collaborations.models.Reservation;
import tn.esprit.wellbeing.modules.collaborations.services.IReservationService;

@RestController
public class ReservationRestController {

	@Autowired
	IReservationService reservationService;

	// http://localhost:8090/pidev/retrieve-all-reservation
	@GetMapping("/retrieve-all-reservation")
	public List<Reservation> retrieveAllReservations() {
		List<Reservation> list = reservationService.retrieveAllReservations();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-reservation/{reservation-id}
	@GetMapping("/retrieve-reservation/{reservation-id}")
	public Reservation retrieveReservation(@PathVariable("reservation-id") Long rsvId) {
		return reservationService.retrieveReservation(rsvId);
	}

	// http://localhost:8090/pidev/add-reservation
	@PostMapping("/add-reservation")
	public Reservation addReservation(@RequestBody Reservation rsv ,Long offerId) {
		Reservation reservation = reservationService.addReservation(rsv, offerId);
		return reservation;
	}

	// http://localhost:8090/pidev/remove-reservation/{reservation-id}
	@DeleteMapping("/remove-reservation/{reservation-id}")
	public void removeReservation(@PathVariable("reservation-id") Long rsvId) {
		reservationService.deleteReservation(rsvId);
	}

	// http://localhost:8090/pidev/modify-reservation
	@PutMapping("/modify-reservation")
	public Reservation updateReservation(@RequestBody Reservation rsv) {
		return reservationService.updateReservation(rsv);
	}

}
