package tn.esprit.wellbeing.modules.collaborations.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.collaborations.models.Offre;

@Repository
public interface OffreRepository extends CrudRepository<Offre, Long> {

	
	@Query("SELECT o FROM Offre o WHERE o.startDate BETWEEN :dateBefore AND :dateAfter")
	List<Offre> findByOfferDateBetween(@Param("dateBefore") Date dateBefore, @Param("dateAfter") Date dateAfter);
	
	

}
