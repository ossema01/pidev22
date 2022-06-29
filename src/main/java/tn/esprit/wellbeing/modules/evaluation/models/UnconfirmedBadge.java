package tn.esprit.wellbeing.modules.evaluation.models;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UnconfirmedBadge extends Badge {
	
	private int votesNbrNeeded;
	
	private int currentVoteNbr;
	
	public int getVotesNbrNeeded() {
		return votesNbrNeeded;
	}
	public void setVotesNbrNeeded(int votesNbrNeeded) {
		this.votesNbrNeeded = votesNbrNeeded;
	}
	public int getCurrentVoteNbr() {
		return currentVoteNbr;
	}
	public void setCurrentVoteNbr(int currentVoteNbr) {
		this.currentVoteNbr = currentVoteNbr;
	}
	

}
