package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;

import tn.esprit.wellbeing.modules.occurences.models.Event;

public interface IEventService {
	List<Event> retrieveAllEvents(); 
	Event createEvent(Event event);
	void deleteEvent(Long id);
	Event updateEvent(Event event);
	Event retrieveEvent(Long id);
}
