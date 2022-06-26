package tn.esprit.wellbeing.modules.occurences.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import tn.esprit.wellbeing.modules.occurences.SubscriptionStatus;

public class Subscription {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String createdBy; 
	private String updatedBy; 
	 @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date createdAt; 
	private Date updatedAt; 
	private SubscriptionStatus status;
}
