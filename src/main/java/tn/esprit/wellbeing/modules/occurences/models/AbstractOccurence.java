package tn.esprit.wellbeing.modules.occurences.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.collaborations.models.Offre;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.ratings.HasRating;
import tn.esprit.wellbeing.modules.feedback.ratings.Rating;
import tn.esprit.wellbeing.modules.feedback.ratings.RatingByUser;
import tn.esprit.wellbeing.modules.occurences.OccurenceStatus;
import tn.esprit.wellbeing.modules.occurences.Repetition;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

@MappedSuperclass
public class AbstractOccurence extends SuperEntity implements HasRating {
    private String theme;
	private String title; 
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
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
	@OneToMany
	private List<User> participants = new ArrayList<>();
	
	//private Long userId;




	public String getTitle() {
		return title;
	}
	public List<User> getParticipants() {
		return participants;
	}
	public void setParticipants(List<User> participants) {
		this.participants = participants;
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
	@Override
	public Rating getTotalRating() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<RatingByUser> getRatings() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addRating(RatingByUser rating) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean removeRating(RatingByUser rating) {
		// TODO Auto-generated method stub
		return false;
	}
	public AbstractOccurence() {
	}
	public AbstractOccurence(Long id, String title, String description) {
		super(id);
		this.title = title;
		this.description = description;
	}



}
