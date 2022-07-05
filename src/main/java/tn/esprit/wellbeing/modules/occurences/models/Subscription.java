package tn.esprit.wellbeing.modules.occurences.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.occurences.SubscriptionStatus;

@Entity
public class Subscription extends SuperEntity implements Serializable{

	private SubscriptionStatus status;


	public SubscriptionStatus getStatus() {
		return status;
	}
	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}
	
}
