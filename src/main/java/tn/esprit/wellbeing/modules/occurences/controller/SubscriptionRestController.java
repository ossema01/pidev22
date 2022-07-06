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

import tn.esprit.wellbeing.modules.occurences.models.Subscription;
import tn.esprit.wellbeing.modules.occurences.services.ISubscriptionService;
@RestController
public class SubscriptionRestController {

	@Autowired
	ISubscriptionService subscriptionService;

	// http://localhost:8090/pidev/retrieve-all-subscriptions
	@GetMapping("/retrieve-all-subscriptions")
	public List<Subscription> retrieveAllSubscriptions() {
		List<Subscription> list = subscriptionService.retrieveAllSubscriptions();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-subscription/{subscription-id}
	@GetMapping("/retrieve-subscription/{subscription-id}")
	public Subscription retrieveSubscription(@PathVariable("subscription-id") Long subscriptionId) {
		return subscriptionService.retrieveSubscription(subscriptionId);
	}

	// http://localhost:8090/pidev/create-subscription
	@PostMapping("/create-subscription")
	public Subscription createSubscription(@RequestBody Subscription subs) {
		Subscription subscription = subscriptionService.createSubscription(subs);
		return subscription;
	}

	// http://localhost:8090/pidev/remove-subscription/{subscription-id}
	@DeleteMapping("/remove-subscription/{subscription-id}")
	public void deleteSubscription(@PathVariable("subscription-id") Long subscriptionId) {
		subscriptionService.deleteSubscription(subscriptionId);
	}

	// http://localhost:8090/pidev/modify-subscription
	@PutMapping("/modify-subscription")
	public Subscription updateSubscription(@RequestBody Subscription subs) {
		return subscriptionService.updateSubscription(subs);
	}

}
