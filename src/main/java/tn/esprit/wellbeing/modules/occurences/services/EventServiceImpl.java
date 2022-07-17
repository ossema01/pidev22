package tn.esprit.wellbeing.modules.occurences.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.occurences.OccurenceRequestStatus;
import tn.esprit.wellbeing.modules.occurences.models.Activity;
import tn.esprit.wellbeing.modules.occurences.models.Event;
import tn.esprit.wellbeing.modules.occurences.models.OccurenceRequest;
import tn.esprit.wellbeing.modules.occurences.repositories.ActivityRepository;
import tn.esprit.wellbeing.modules.occurences.repositories.EventRepository;
import tn.esprit.wellbeing.modules.occurences.repositories.OccurenceRequestRepository;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.repository.UserRepository;
@Service
public class EventServiceImpl implements IEventService {
	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	OccurenceRequestRepository occurenceRequestRepository;
	private static final Logger l = LogManager.getLogger(EventServiceImpl.class);

	@Override
	public List<Event> retrieveAllEvents() {
		List<Event> events = null;
		try {

			l.info("In Method retrieveAllEvents :");
			events = (List<Event>) eventRepository.findAll();
			l.debug("connexion à la DB Ok :");
			for (Event event : events) {
				l.debug("event :" + event);
			}
			l.info("Out of Method retrieveAllEvents with Success" + events.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAllEvents with Errors : " + e);
		}

		return events;
	}

	@Override
	public Event createEvent(Event event) {
		Event event_saved = null;

		event.setCreatedAt(LocalDateTime.now());
		event.setUpdatedAt(LocalDateTime.now());

		try {
			l.info("In Method createEvent :");
			event_saved = eventRepository.save(event);
			l.info("Out of Method createEvent with Success" + event_saved);

		} catch (Exception e) {
			l.error("Out of Method createEvent with Errors : " + e);
		}

		return event_saved;

	}

	@Override
	public void deleteEvent(Long id) {
		try {
			l.info("In Method deleteEvent :");
			eventRepository.deleteById(id);
			l.info("Out of Method deleteEvent with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteEvent with Errors : " + e);
		}
	}

	@Override
	public Event updateEvent(Event event) {

		Event eventUpdated = null;

		try {
			l.info("In Method updateEvent :");
			eventUpdated = eventRepository.save(event);
			l.info("Out of Method updateEvent with Success" + eventUpdated);

		} catch (Exception e) {
			l.error("Out of Method updateEvent with Errors : " + e);
		}

		return eventUpdated;
	}
 
	@Override
	public Event retrieveEvent(Long id) {
		Optional<Event> event = null;
		try {
			l.info("In Method retrieveEvent :");
			event = eventRepository.findById(id);
			if (event.isPresent()) {
				l.info("Out of Method retrieveEvent with Success" + event.get());
				return event.get();
			}

		} catch (Exception e) {
			l.error("Out of Method retrieveEvent with Errors : " + e);
		}
		return null;

	}
	
	@Override
	public void addParticipantToEvent(Long userId, Long eventId) {
		User user = userRepository.findById(userId).orElse(null);
		Event event = eventRepository.findById(eventId).orElse(null);
		event.getParticipants().add(user);
		eventRepository.save(event);
		
	}

	@Override
	public void addActivityToEvent(Long activityId, Long eventId) {
		Activity activity = activityRepository.findById(activityId).orElse(null);
		Event event = eventRepository.findById(eventId).orElse(null);
		event.getActivitiesList().add(activity);
		eventRepository.save(event);
		
	}

	@Override
	public List<User> findUsersWithSameInterests(String interest) {
		Iterable<User> usersList;
		List <User> usersWithSameInterest = null;
		/*usersList = userRepository.findAll();
		for (User user : usersList) {
			if(user.getInterests().contains(interest)) {
				usersWithSameInterest.add(user);
			}
		}*/
		return usersWithSameInterest;
	}

	@Override
	public void acceptOccurenceRequest(Long eventId, Long OccRequestId) {
		OccurenceRequest occReq = occurenceRequestRepository.findById(OccRequestId).orElse(null);
		Event event = eventRepository.findById(eventId).orElse(null);
        occReq.setStatus(OccurenceRequestStatus.Accepted);
        event.getRequests().add(occReq);
        eventRepository.save(event);
        occurenceRequestRepository.save(occReq);
	}
	
	@Override
	public void rejectOccurenceRequest(Long eventId, Long OccRequestId) {
		OccurenceRequest occReq = occurenceRequestRepository.findById(OccRequestId).orElse(null);
		Event event = eventRepository.findById(eventId).orElse(null);
        occReq.setStatus(OccurenceRequestStatus.Rejected);
        event.getRequests().add(occReq);
        eventRepository.save(event);
        occurenceRequestRepository.save(occReq);
	}

}
