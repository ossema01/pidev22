package tn.esprit.wellbeing.modules.occurences.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.wellbeing.modules.occurences.models.Invitation;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Long> {

}
