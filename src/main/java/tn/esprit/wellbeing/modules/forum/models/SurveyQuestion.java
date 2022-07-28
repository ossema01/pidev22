package tn.esprit.wellbeing.modules.forum.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class SurveyQuestion extends SuperEntity {

	private String question;


	@OneToMany(targetEntity = SurveyResponse.class,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SurveyResponse> responses;

	public List<SurveyResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<SurveyResponse> responses) {
		this.responses = responses;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
