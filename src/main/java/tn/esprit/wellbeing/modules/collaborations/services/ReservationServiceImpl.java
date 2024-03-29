package tn.esprit.wellbeing.modules.collaborations.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.collaborations.models.Offre;
import tn.esprit.wellbeing.modules.collaborations.models.Reservation;
import tn.esprit.wellbeing.modules.collaborations.repositories.ReservationRepository;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationType;
import tn.esprit.wellbeing.modules.notifications.provider.NotificationProviderFactory;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Service
public class ReservationServiceImpl implements IReservationService {

	@Autowired
	ReservationRepository reservationRepo;
	
	@Autowired
     IOffreService offreService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notifService;
	
	

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
	public Reservation addReservation(Reservation rsv, Long offerId) {

			l.info("In Method addReservation");
			Offre offer = offreService.retrieveOffre(offerId);
			if (offer ==null) {
				throw new RuntimeException("No offer found with offerId: " +offerId) ;
			}
			int reservedPlaces = 0 ;
			for (Reservation reservation : offer.getRsvList()) {
				reservedPlaces += reservation.getNbrOfreservedPlaces();
			}
			reservedPlaces += rsv.getNbrOfreservedPlaces();
			if (reservedPlaces > offer.getNbOfAvailablePlaces()) {
				throw new RuntimeException("You cannot reserve for: " + rsv.getNbrOfreservedPlaces());
			}
			offer.addReservation(rsv);
			offreService.updateOffre(offer);
			String msg = "Your reservation for the offer "+ offer.getTitle() +" is done successfully, thanks for your interest";
			String userName = userService.getCurrentUser().getUsername();
			Notification notif = NotificationProviderFactory.getDefaultProvider().getNotification(userName, msg, NotificationType.MAIL);
			notifService.sendNotification(notif);
		return rsv;
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
