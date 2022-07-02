package tn.esprit.wellbeing.modules.forum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.forum.models.SurveyResponse;

@Repository
public interface SurveyResponseRepository extends CrudRepository<SurveyResponse, Long> {

}
