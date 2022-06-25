package tn.esprit.wellbeing.modules.feedback.ratings;

import javax.persistence.Entity;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class RatingByUser extends SuperEntity {

	private float rate;
	
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
}
