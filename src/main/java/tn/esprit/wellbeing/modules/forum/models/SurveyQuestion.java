package tn.esprit.wellbeing.modules.forum.models;

import tn.esprit.wellbeing.models.SuperEntity;

public class SurveyQuestion extends SuperEntity {

	private String question;
	private int order;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
