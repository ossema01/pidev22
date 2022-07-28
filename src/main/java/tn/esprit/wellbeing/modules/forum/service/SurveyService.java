package tn.esprit.wellbeing.modules.forum.service;

import tn.esprit.wellbeing.modules.forum.models.Survey;

public interface SurveyService extends AbstractForumObjectService<Survey> {
	
	void addSurveyQuestion(Long surveyId,String question);
	void respond(Long questId,String response);
	Survey[] findByCreatedBy(String username);

}
