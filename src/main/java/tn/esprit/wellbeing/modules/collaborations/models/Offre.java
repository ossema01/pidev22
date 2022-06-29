package tn.esprit.wellbeing.modules.collaborations.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.feedback.ratings.HasRating;
import tn.esprit.wellbeing.modules.feedback.ratings.Rating;
import tn.esprit.wellbeing.modules.feedback.ratings.RatingByUser;

@Entity
public class Offre extends SuperEntity implements HasRating{

	private String description;

	private String title;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date startDate;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date endDate;

	private int nbOfAvailablePlaces;

	@OneToMany
	private Set<Reservation> rsvList;

	public Offre() {
		super();
	}

	public Offre(String description, String title, Date startDate, Date endDate, int nbOfAvailablePlaces) {
		super();
		this.description = description;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.nbOfAvailablePlaces = nbOfAvailablePlaces;
	}

	public Offre(String description, String title, Date startDate, Date endDate, int nbOfAvailablePlaces,
			Set<Reservation> rsvList) {
		super();
		this.description = description;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.nbOfAvailablePlaces = nbOfAvailablePlaces;
		this.rsvList = rsvList;
	}

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

}
