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
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

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
	
	// http://localhost:8090/pidev/add-activity-to-event/{activity-id}/{event-id}
	@PostMapping("/add-activity-to-event/{activity-id}/{event-id}")
	public void addActivityToEvent(@PathVariable("activity-id") Long activityId,@PathVariable("event-id") Long eventId) {
		eventService.addActivityToEvent(activityId,eventId);
	}
	
	
	// http://localhost:8090/pidev/add-participant-to-event/{participant-id}/{event-id}
	@PostMapping("/add-participant-to-event/{participant-id}/{event-id}")
	public void addParticipantToEvent(@PathVariable("participant-id") Long participantId,@PathVariable("event-id") Long eventId) {
		eventService.addParticipantToEvent(participantId,eventId);
	}
	// http://localhost:8090/pidev/find-users-with-same-interests/{interest}
	@GetMapping("/find-users-with-same-interests/{interest}")
	public List<User> findUsersWithSameInterests(@PathVariable("interest") String interest) {
		return eventService.findUsersWithSameInterests(interest);
	}
	
	// http://localhost:8090/pidev/accept-occurence-Request/{event-id}/{occReq-id}
	@PostMapping("/accept-occurence-Request/{event-id}/{occReq-id}")
	public void acceptOccurenceRequest(@PathVariable("event-id") Long eventId , @PathVariable("occReq-id") Long occReqId) {
		 eventService.acceptOccurenceRequest(eventId,occReqId);
	}
	// http://localhost:8090/pidev/reject-occurence-Request/{event-id}/{occReq-id}
		@PostMapping("/reject-occurence-Request/{event-id}/{occReq-id}")
		public void rejectOccurenceRequest(@PathVariable("event-id") Long eventId , @PathVariable("occReq-id") Long occReqId) {
			 eventService.rejectOccurenceRequest(eventId,occReqId);
		}
}
