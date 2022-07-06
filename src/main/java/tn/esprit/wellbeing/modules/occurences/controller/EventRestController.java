package tn.esprit.wellbeing.modules.occurences.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.occurences.models.Event;
import tn.esprit.wellbeing.modules.occurences.services.IEventService;

@RestController
public class EventRestController {
	@Autowired
	IEventService eventService;

	// http://localhost:8090/pidev/retrieve-all-events
	@GetMapping("/retrieve-all-events")
	public List<Event> retrieveAllEvents() {
		List<Event> list = eventService.retrieveAllEvents();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-event/{event-id}
	@GetMapping("/retrieve-event/{event-id}")
	public Event retrieveEvent(@PathVariable("event-id") Long eventId) {
		return eventService.retrieveEvent(eventId);
	}

	// http://localhost:8090/pidev/create-event
	@PostMapping("/create-event")
	public Event createEvent(@RequestBody Event ev) {
		Event event = eventService.createEvent(ev);
		return event;
	}

	// http://localhost:8090/pidev/remove-event/{event-id}
	@DeleteMapping("/remove-event/{event-id}")
	public void deleteEvent(@PathVariable("event-id") Long eventId) {
		eventService.deleteEvent(eventId);
	}

	// http://localhost:8090/pidev/modify-event
	@PutMapping("/modify-event")
	public Event updateEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);
	}	
}
