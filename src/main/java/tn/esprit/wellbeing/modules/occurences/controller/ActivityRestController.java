package tn.esprit.wellbeing.modules.occurences.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.occurences.models.Activity;
import tn.esprit.wellbeing.modules.occurences.services.IActivityService;

@RestController
public class ActivityRestController {

	@Autowired
	IActivityService activityService;

	// http://localhost:8090/pidev/retrieve-all-activities
	@GetMapping("/retrieve-all-activities")
	public List<Activity> retrieveAllActivities() {
		List<Activity> list = activityService.retrieveAllActivities();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-activity/{activity-id}
	@GetMapping("/retrieve-activity/{activity-id}")
	public Activity retrieveActivity(@PathVariable("activity-id") Long activId) {
		return activityService.retrieveActivity(activId);
	}

	// http://localhost:8090/pidev/add-activity
	@PostMapping("/add-activity")
	public Activity createActivity(@RequestBody Activity activ) {
		Activity activity = activityService.createActivity(activ);
		return activity;
	}

	// http://localhost:8090/pidev/remove-activity/{activity-id}
	@DeleteMapping("/remove-activity/{activity-id}")
	public void deleteActivity(@PathVariable("activity-id") Long activId) {
		activityService.deleteActivity(activId);
	}

	// http://localhost:8090/pidev/modify-activity
	@PutMapping("/modify-activity")
	public Activity updateActivity(@RequestBody Activity activ) {
		return activityService.updateActivity(activ);
	}
	// http://localhost:8090/pidev/add-participant-to-activity/{participant-id}/{activity-id}
	@PostMapping("/add-participant-to-activity/{participant-id}/{activity-id}")
	public void addParticipantToActivity(@PathVariable("participant-id") Long participantId,@PathVariable("activity-id") Long activityId) {
		activityService.addParticipantToActivity(participantId,activityId);
	}
}
