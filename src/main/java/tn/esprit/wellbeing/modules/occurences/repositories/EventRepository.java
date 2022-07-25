package tn.esprit.wellbeing.modules.occurences.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.occurences.models.Event;
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
	@Query("FROM Event e")
	List<Event> retrieveAllEvents();
}
