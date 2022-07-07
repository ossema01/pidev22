package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;

import tn.esprit.wellbeing.modules.occurences.models.Event;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

public interface IEventService {
	List<Event> retrieveAllEvents(); 
	Event createEvent(Event event);
	void deleteEvent(Long id);
	Event updateEvent(Event event);
	Event retrieveEvent(Long id);
	void addParticipantToEvent(Long userId,Long eventId);
	void addActivityToEvent(Long activityId,Long eventId);
	List<User> findUsersWithSameInterests(String interest);
	void acceptOccurenceRequest(Long eventId,Long OccRequestId);
	void rejectOccurenceRequest(Long eventId,Long OccRequestId);

}
