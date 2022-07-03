package tn.esprit.wellbeing.modules.forum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.wellbeing.modules.forum.models.SurveyQuestion;

@Repository
public interface SurveyQuestionRepository extends CrudRepository<SurveyQuestion, Long> {

}
