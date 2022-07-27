package tn.esprit.wellbeing.modules.forum.models;

import javax.persistence.Entity;

import tn.esprit.wellbeing.modules.forum.AbstractForumObject;

@Entity
public class Survey extends AbstractForumObject {
	
	private SurveyContent surveyContent;
	
	public SurveyContent getSurveyContent() {
		return surveyContent;
	}

	public void setSurveyContent(SurveyContent surveyContent) {
		this.surveyContent = surveyContent;
	}

}
