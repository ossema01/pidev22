package tn.esprit.wellbeing.modules.occurences.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.occurences.models.Event;
import tn.esprit.wellbeing.modules.occurences.models.Subscription;
import tn.esprit.wellbeing.modules.occurences.repositories.EventRepository;
import tn.esprit.wellbeing.modules.occurences.repositories.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {
	@Autowired
	SubscriptionRepository subscriptionRepository;
	@Autowired
	EventRepository eventRepository;
	private static final Logger l = LogManager.getLogger(SubscriptionServiceImpl.class);

	@Override
	public List<Subscription> retrieveAllSubscriptions() {
		List<Subscription> subscriptions = null;
		try {

			l.info("In Method retrieveAllSubscriptions :");
			subscriptions = (List<Subscription>) subscriptionRepository.findAll();
			l.debug("connexion à la DB Ok :");
			for (Subscription subscription : subscriptions) {
				l.debug("subscription :" + subscription);
			}
			l.info("Out of Method retrieveAllSubscriptions with Success" + subscriptions.size());
		} catch (Exception e) {
			l.error("Out of Method retrieveAllSubscriptions with Errors : " + e);
		}

		return subscriptions;
	}

	@Override
	public Subscription createSubscription(Subscription subscription) {
		Subscription subscription_saved = null;

		try {
			l.info("In Method addOccurenceRequest :");
			subscription_saved = subscriptionRepository.save(subscription);
			l.info("Out of Method createSubscription with Success" + subscription_saved);

		} catch (Exception e) {
			l.error("Out of Method createSubscription with Errors : " + e);
		}

		return subscription_saved;

	}

	@Override
	public void deleteSubscription(Long id) {
		try {
			l.info("In Method deleteSubscription :");
			subscriptionRepository.deleteById(id);
			l.info("Out of Method deleteSubscription with Success");

		} catch (Exception e) {
			l.error("Out of Method deleteSubscription with Errors : " + e);
		}
	}

	@Override
	public Subscription updateSubscription(Subscription subscription) {

		Subscription subscriptionUpdated = null;

		try {
			l.info("In Method updateSubscription :");
			subscriptionUpdated = subscriptionRepository.save(subscription);
			l.info("Out of Method updateSubscription with Success" + subscriptionUpdated);

		} catch (Exception e) {
			l.error("Out of Method updateSubscription with Errors : " + e);
		}

		return subscriptionUpdated;
	}

	@Override
	public Subscription retrieveSubscription(Long id) {
		Optional<Subscription> subscription = null;
		try {
			l.info("In Method retrieveOccurenceRequest :");
			subscription = subscriptionRepository.findById(id);
			if (subscription.isPresent()) {
				l.info("Out of Method retrieveOccurenceRequest with Success" + subscription.get());
				return subscription.get();
			}

		} catch (Exception e) {
			l.error("Out of Method retrieveOccurenceRequest with Errors : " + e);
		}
		return null;

	}

	@Override
	public void addSubscriptionToEvent(Long subscriptionId, Long eventId) {
     Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);
     Event event = eventRepository.findById(eventId).orElse(null);
     event.getSubscriptionList().add(subscription);
     eventRepository.save(event);
	}
}
