package tn.esprit.wellbeing.modules.occurences.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.occurences.OccurenceStatus;
import tn.esprit.wellbeing.modules.occurences.Repetition;

public class AbstractOccurence {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String title; 
	private String description; 
	private String createdBy; 
	private String updatedBy; 
	 @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date createdAt; 
	private Date updatedAt; 
	private Date startDate; 
	private Date endDate; 
	@Enumerated(EnumType.STRING)
	private OccurenceStatus status; 
	@Enumerated(EnumType.STRING)
	private Repetition repetition; 
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OccurenceRequest> requests = new ArrayList<>();



}