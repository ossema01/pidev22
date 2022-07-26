package tn.esprit.wellbeing.modules.forum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.forum.models.Advice;

@Repository
public interface AdviceRepository extends CrudRepository<Advice, Long> {

}
