package tn.esprit.wellbeing.modules.evaluation.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.evaluation.models.Badge;

@Repository
public interface BadgeRepository extends CrudRepository<Badge, Long>{

}
