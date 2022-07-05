package tn.esprit.wellbeing.modules.occurences.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.collaborations.models.Offre;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.occurences.OccurenceStatus;
import tn.esprit.wellbeing.modules.occurences.Repetition;
@Entity
public class AbstractOccurence extends SuperEntity {

	private String title; 
	private String description; 
	 @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date startDate; 
	private Date endDate; 
	@Enumerated(EnumType.STRING)
	private OccurenceStatus status; 
	@Enumerated(EnumType.STRING)
	private Repetition repetition; 
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<OccurenceRequest> requests = new ArrayList<>();
	@OneToMany
	private Collection<Invitation> invitationList = new ArrayList<>();
	@OneToMany
	private Collection<FavoriteByUser> FavoriteByUserList = new ArrayList<>();

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public OccurenceStatus getStatus() {
		return status;
	}
	public void setStatus(OccurenceStatus status) {
		this.status = status;
	}
	public Repetition getRepetition() {
		return repetition;
	}
	public void setRepetition(Repetition repetition) {
		this.repetition = repetition;
	}
	public Collection<OccurenceRequest> getRequests() {
		return requests;
	}
	public void setRequests(Collection<OccurenceRequest> requests) {
		this.requests = requests;
	}
	public Collection<Invitation> getInvitationList() {
		return invitationList;
	}
	public void setInvitationList(Collection<Invitation> invitationList) {
		this.invitationList = invitationList;
	}
	public Collection<FavoriteByUser> getFavoriteByUserList() {
		return FavoriteByUserList;
	}
	public void setFavoriteByUserList(Collection<FavoriteByUser> favoriteByUserList) {
		FavoriteByUserList = favoriteByUserList;
	}



}
