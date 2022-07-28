package tn.esprit.wellbeing.modules.feedback.comments;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends CrudRepository<Comment, Long> {
	
	Comment[] findAllByCreatedBy(String username);

}
