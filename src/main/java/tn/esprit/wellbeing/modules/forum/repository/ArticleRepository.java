package tn.esprit.wellbeing.modules.forum.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.forum.models.Article;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

	@Modifying
	@Query("UPDATE Article SET anonymous=1 WHERE id = :id ")
	void unAnonymize(@Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Article  SET anonymous=0 WHERE id = :id ")
	void anonymize(@Param("id") Long id);

	@Modifying
	@Query("UPDATE Article SET suspendedComments=0 WHERE id = :id ")
	void activateComments(@Param("id") Long forumObjctId);

	@Modifying
	@Query("UPDATE Article  SET suspendedComments=1 WHERE id = :id ")
	void suspendComments(@Param("id") Long forumObjctId);

	User[] findAllByCreatedBy(String username);
}
