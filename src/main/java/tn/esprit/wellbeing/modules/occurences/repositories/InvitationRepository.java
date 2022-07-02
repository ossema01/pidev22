package tn.esprit.wellbeing.modules.occurences.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.wellbeing.modules.occurences.models.Invitation;

public interface InvitationRepository extends CrudRepository<Invitation, String> {

}
