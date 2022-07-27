package tn.esprit.wellbeing.modules.collaborations.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.ratings.HasRating;
import tn.esprit.wellbeing.modules.feedback.ratings.Rating;
import tn.esprit.wellbeing.modules.feedback.ratings.RatingByUser;
import tn.esprit.wellbeing.modules.feedback.ratings.RatingService;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Offre extends SuperEntity implements HasRating {

	private String description;

	private String title;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date startDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	private int nbOfAvailablePlaces;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RatingByUser> ratings = new HashSet<>() ;


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Reservation> rsvList = new HashSet<>();
	
	
	@Transient
	private Rating totalRating;

	public Set<Reservation> getRsvList() {
		return rsvList;
	}

	public void setRsvList(Set<Reservation> rsvList) {
		this.rsvList = rsvList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getNbOfAvailablePlaces() {
		return nbOfAvailablePlaces;
	}

	public void setNbOfAvailablePlaces(int nbOfAvailablePlaces) {
		this.nbOfAvailablePlaces = nbOfAvailablePlaces;
	}

	@Override
	public Rating getTotalRating() {
		this.totalRating = WellBeingApplication.context.getBean(RatingService.class).calculateRating(this);

		if (this.totalRating == null) {
			this.totalRating = new Rating(0, 0);
		}
		
		System.out.print(this.totalRating);
		return this.totalRating;

	}

	@Override
	public Set<RatingByUser> getRatings() {

		return this.ratings;
	}

	@Override
	public void addRating(RatingByUser rating) {

		this.ratings.add(rating);

	}

	@Override
	public boolean removeRating(RatingByUser rating) {
		return this.ratings.remove(rating);
	}
	
	public void addReservation(Reservation rsv) {
		
		this.rsvList.add(rsv);
	}

}
