package tn.esprit.wellbeing.modules.collaborations.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.collaborations.models.Reservation;


@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
