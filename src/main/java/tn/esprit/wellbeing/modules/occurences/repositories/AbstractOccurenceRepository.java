package tn.esprit.wellbeing.modules.occurences.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.occurences.models.AbstractOccurence;
@Repository
public interface AbstractOccurenceRepository extends CrudRepository<AbstractOccurence, Long>  {

}
