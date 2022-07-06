package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;

import tn.esprit.wellbeing.modules.occurences.models.Activity;

public interface IActivityService {
	List<Activity> retrieveAllActivities(); 
	Activity createActivity(Activity activity);
	void deleteActivity(Long id);
	Activity updateActivity(Activity en);
	Activity retrieveActivity(Long id);
}
