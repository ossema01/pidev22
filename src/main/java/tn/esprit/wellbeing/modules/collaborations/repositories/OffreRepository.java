package tn.esprit.wellbeing.modules.collaborations.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.collaborations.models.Offre;

@Repository
public interface OffreRepository extends CrudRepository<Offre, Long> {

}
