package tn.esprit.wellbeing.modules.forum.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.forum.models.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long>{

	@Modifying
	@Query("UPDATE post_ SET anonymous=1 WHERE id = :id ")
	void unAnonymize(@Param("id") Long id);
	
	@Modifying
	@Query("UPDATE post_  SET anonymous=0 WHERE id = :id ")
	void anonymize(@Param("id") Long id);

	@Modifying
	@Query("UPDATE post_ SET suspendedComments=0 WHERE id = :id ")
	void activateComments(@Param("id") Long forumObjctId);

	@Modifying
	@Query("UPDATE post_  SET suspendedComments=1 WHERE id = :id ")
	void suspendComments(@Param("id") Long forumObjctId);
	
}
