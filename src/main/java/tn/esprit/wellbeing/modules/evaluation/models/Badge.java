package tn.esprit.wellbeing.modules.evaluation.models;

import javax.persistence.Entity;

import tn.esprit.wellbeing.models.SuperEntity;

@Entity
public class Badge extends SuperEntity {
	
	private String title;
	private String description;
	private int pointsNbrToGetBadge;
	private int votesNumberNeeded;
	private int currentVoteNumber;

	
	public Badge() {
		super();
	}

	public Badge(String title, String description, int pointsNbrToGetBadge, int votesNumberNeeded,
			int currentVoteNumber) {
		super();
		this.title = title;
		this.description = description;
		this.pointsNbrToGetBadge = pointsNbrToGetBadge;
		this.votesNumberNeeded = votesNumberNeeded;
		this.currentVoteNumber = currentVoteNumber;
	}

	public int getVotesNumberNeeded() {
		return votesNumberNeeded;
	}

	public void setVotesNumberNeeded(int votesNumberNeeded) {
		this.votesNumberNeeded = votesNumberNeeded;
	}

	public int getCurrentVoteNumber() {
		return currentVoteNumber;
	}

	public void setCurrentVoteNumber(int currentVoteNumber) {
		this.currentVoteNumber = currentVoteNumber;
	}

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

	public int getPointsNbrToGetBadge() {
		return pointsNbrToGetBadge;
	}

	public void setPointsNbrToGetBadge(int pointsNbrToGetBadge) {
		this.pointsNbrToGetBadge = pointsNbrToGetBadge;
	}

}
