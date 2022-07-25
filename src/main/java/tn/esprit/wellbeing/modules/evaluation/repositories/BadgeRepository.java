package tn.esprit.wellbeing.modules.evaluation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.evaluation.models.Badge;

@Repository
public interface BadgeRepository extends CrudRepository<Badge, Long>{

	@Query("SELECT b FROM Badge b WHERE b.currentVoteNumber >= b.votesNumberNeeded")
	List<Badge> findConfirmedBadges();

	@Query("SELECT b FROM Badge b WHERE b.currentVoteNumber < b.votesNumberNeeded")
	List<Badge> findUnConfirmedBadges();

	@Query("SELECT b FROM Badge b WHERE b.currentVoteNumber >= b.votesNumberNeeded AND b.pointsNbrToGetBadge >= :note ")
	List<Badge> getUserBadges(@Param("note") int note); 
	
	
	

}
