package tn.esprit.wellbeing.modules.collaborations.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.wellbeing.modules.collaborations.models.Reservation;
import tn.esprit.wellbeing.modules.collaborations.repositories.ReservationRepository;

@Service
public class ReservationServiceImpl implements IReservationService {

	@Autowired
	ReservationRepository reservationRepo;

	private static final Logger l = LogManager.getLogger(ReservationServiceImpl.class);

	@Override
	public List<Reservation> retrieveAllReservations() {
		List<Reservation> reservations = null;
		try {

			l.info("In Method retrieveAllReservations");
			reservations = (List<Reservation>) reservationRepo.findAll();
			l.debug("connexion à la DB Ok :");
			for (Reservation reservation : reservations) {
				l.debug("reservations :" + reservation.getId());
			}
			l.info("Out of Method retrieveAllReservations with Success : " + reservations.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAllReservations with Errors : " + e);
		}

		return reservations;
	}

	@Override
	public Reservation addReservation(Reservation rsv) {
		Reservation rsv_saved = null;

		try {
			l.info("In Method addReservation");
			rsv_saved = reservationRepo.save(rsv);
			l.info("Out of Method addReservation with Success : " + rsv_saved.getId());

		} catch (Exception e) {
			l.error("Out of Method addReservation with Errors : " + e);
		}

		return rsv_saved;
	}

	@Override
	public void deleteReservation(Long id) {
		try {
			l.info("In Method deleteReservation");
			reservationRepo.deleteById(id);
			l.info("Out of Method deleteReservation with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteReservation with Errors : " + e);
		}
	}

	@Override
	public Reservation updateReservation(Reservation rsv) {
		Reservation reservationUpdated = null;

		try {
			l.info("In Method reservationUpdated");
			reservationUpdated = reservationRepo.save(rsv);
			l.info("Out of Method reservationUpdated with Success : " + reservationUpdated.getId());

		} catch (Exception e) {
			l.error("Out of Method reservationUpdated with Errors : " + e);
		}

		return reservationUpdated;
	}

	@Override
	public Reservation retrieveReservation(Long id) {
		Optional<Reservation> rsv = null;
		try {
			l.info("In Method retrieveReservation");
			rsv = reservationRepo.findById(id);
			if (rsv.isPresent()) {
				l.info("Out of Method retrieveReservation with Success : " + rsv.get().getId());
				return rsv.get();
			}
		} catch (Exception e) {
			l.error("Out of Method retrieveReservation with Errors : " + e);
		}

		return null;

	}

}
