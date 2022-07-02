package tn.esprit.wellbeing.modules.occurences.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.wellbeing.modules.occurences.models.OccurenceRequest;

@Repository
public interface OccurenceRequestRepository extends CrudRepository<OccurenceRequest, String> {

}
