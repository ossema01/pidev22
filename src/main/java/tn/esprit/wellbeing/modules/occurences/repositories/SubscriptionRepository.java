package tn.esprit.wellbeing.modules.occurences.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.wellbeing.modules.occurences.models.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, String> {

}
