package tn.esprit.wellbeing.modules.forum.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.forum.models.Survey;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
	
	@Modifying
	@Transactional
	@Query("UPDATE Survey SET anonymous=0 WHERE id = :id ")
	void unAnonymize(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Survey  SET anonymous=1 WHERE id = :id ")
	void anonymize(@Param("id") Long id);

	@Modifying
	@Transactional
	@Query("UPDATE Survey SET suspendedComments=0 WHERE id = :id ")
	void activateComments(@Param("id") Long forumObjctId);

	@Modifying
	@Transactional
	@Query("UPDATE Survey  SET suspendedComments=1 WHERE id = :id ")
	void suspendComments(@Param("id") Long forumObjctId);

	Survey[] findAllByCreatedBy(String username);

}
