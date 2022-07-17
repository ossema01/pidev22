package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;

import tn.esprit.wellbeing.modules.occurences.models.Subscription;


public interface ISubscriptionService {
	List<Subscription> retrieveAllSubscriptions(); 
	Subscription createSubscription(Subscription invitation);
	void deleteSubscription(Long id);
	Subscription updateSubscription(Subscription en);
	Subscription retrieveSubscription(Long id);
	void addSubscriptionToEvent (Long subscriptionId, Long eventId);
}
