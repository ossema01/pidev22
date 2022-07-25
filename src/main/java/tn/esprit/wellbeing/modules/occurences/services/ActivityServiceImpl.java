package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.occurences.models.Activity;
import tn.esprit.wellbeing.modules.occurences.models.Event;
import tn.esprit.wellbeing.modules.occurences.repositories.ActivityRepository;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.repository.UserRepository;
@Service
public class ActivityServiceImpl implements IActivityService {
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;
	private static final Logger l = LogManager.getLogger(ActivityServiceImpl.class);

	@Override
	public List<Activity> retrieveAllActivities() {
		List<Activity> activities = null;
		try {

			l.info("In Method retrieveAllActivities :");
			activities = (List<Activity>) activityRepository.findAll();
			l.debug("connexion à la DB Ok :");
			for (Activity activity : activities) {
				l.debug("invitation :" + activity);
			}
			l.info("Out of Method retrieveAllActivities with Success" + activities.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAllActivities with Errors : " + e);
		}

		return activities;
	}

	@Override
	public Activity createActivity(Activity activity) {
		Activity activity_saved = null;

		try {
			l.info("In Method createActivity :");
			activity_saved = activityRepository.save(activity);
			l.info("Out of Method createActivity with Success" + activity_saved);

		} catch (Exception e) {
			l.error("Out of Method createActivity with Errors : " + e);
		}

		return activity_saved;

	}

	@Override
	public void deleteActivity(Long id) {
		try {
			l.info("In Method deleteActivity :");
			activityRepository.deleteById(id);
			l.info("Out of Method deleteActivity with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteActivity with Errors : " + e);
		}
	}

	@Override
	public Activity updateActivity(Activity activity) {

		Activity activityUpdated = null;

		try {
			l.info("In Method updateActivity :");
			activityUpdated = activityRepository.save(activity);
			l.info("Out of Method updateActivity with Success" + activityUpdated);

		} catch (Exception e) {
			l.error("Out of Method updateActivity with Errors : " + e);
		}

		return activityUpdated;
	}

	@Override
	public Activity retrieveActivity(Long id) {
		Optional<Activity> activity = null;
		try {
			l.info("In Method retrieveActivity :");
			activity = activityRepository.findById(id);
			if (activity.isPresent()) {
				l.info("Out of Method retrieveActivity with Success" + activity.get());
				return activity.get();
			}

		} catch (Exception e) {
			l.error("Out of Method retrieveActivity with Errors : " + e);
		}
		return null;

	}
	
	@Override
	public void addParticipantToActivity(Long userId, Long activityId) {
		User user = userRepository.findById(userId).orElse(null);
		Activity activity = activityRepository.findById(activityId).orElse(null);
		activity.getParticipants().add(user);
		activityRepository.save(activity);
		
	}

}
