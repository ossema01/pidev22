package tn.esprit.wellbeing.modules.occurences.models;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import tn.esprit.wellbeing.modules.occurences.OccurenceRequestStatus;

public class OccurenceRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String createdBy; 
	private String updatedBy; 
	 @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date createdAt; 
	private Date updatedAt; 
	@Enumerated(EnumType.STRING)
	private OccurenceRequestStatus status;
}
