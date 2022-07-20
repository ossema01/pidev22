package tn.esprit.wellbeing.modules.collaborations.services;

import java.util.List;

import tn.esprit.wellbeing.modules.collaborations.models.Reservation;

public interface IReservationService {
	
	List<Reservation> retrieveAllReservations(); 
	Reservation addReservation(Reservation rsv, Long offreId);
	void deleteReservation(Long id);
	Reservation updateReservation(Reservation rsv);
	Reservation retrieveReservation(Long id);

}
