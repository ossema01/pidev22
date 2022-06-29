package tn.esprit.wellbeing.modules.collaborations.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.collaborations.models.Collaboration;


@Repository
public interface CollaborationRepository extends CrudRepository<Collaboration, Long> {

}
