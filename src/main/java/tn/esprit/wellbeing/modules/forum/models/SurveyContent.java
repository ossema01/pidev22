package tn.esprit.wellbeing.modules.forum.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class SurveyContent extends SuperEntity{
	
	@OneToMany(targetEntity = SurveyQuestion.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SurveyQuestion> questions;

	public List<SurveyQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<SurveyQuestion> questions) {
		this.questions = questions;
	}	

}
