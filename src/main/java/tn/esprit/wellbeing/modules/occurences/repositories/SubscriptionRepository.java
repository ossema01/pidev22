package tn.esprit.wellbeing.modules.occurences.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.wellbeing.modules.occurences.models.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, String> {

}
