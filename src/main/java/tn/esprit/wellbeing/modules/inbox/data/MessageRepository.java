package tn.esprit.wellbeing.modules.inbox.data;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
