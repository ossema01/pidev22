package tn.esprit.wellbeing.modules.occurences.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.wellbeing.modules.occurences.models.OccurenceRequest;

public interface OccurenceRequestRepository extends CrudRepository<OccurenceRequest, String> {

}
