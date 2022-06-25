package tn.esprit.wellbeing.feedback.ratings;

public class Rating {

	private final int nbOfVotes;
	private final float totalRating;

	public Rating(float rate, int nbOfVotes) {
		this.nbOfVotes = nbOfVotes;
		this.totalRating = rate;
	}

	public int getNbOfVotes() {
		return nbOfVotes;
	}

	public float getTotalRating() {
		return totalRating;
	}

}
