package tn.esprit.wellbeing.modules.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.forum.models.Recommendation;

@Repository
public interface RecommendationReposiory extends CrudRepository<Recommendation, Long> {

	@Query("UPDATE Recommendation p SET p.anonymous=1 WHERE p.id = :id ")
	void unAnonymize(@Param("id") Long id);

	@Query("UPDATE Recommendation p SET p.anonymous=0 WHERE p.id = :id ")
	void anonymize(@Param("id") Long id);

	@Query("UPDATE Recommendation p SET p.suspendedComments=0 WHERE p.id = :id ")
	void activateComments(@Param("id") Long forumObjctId);

	@Query("UPDATE Recommendation p SET p.suspendedComments=1 WHERE p.id = :id ")
	void suspendComments(@Param("id") Long forumObjctId);
}
