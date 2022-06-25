package tn.esprit.wellbeing.feedback.ratings;

import java.util.Set;

public interface HasRating {
	
	Rating getTotalRating();
	
	Set<RatingByUser> getRatings();
	
	void addRating(RatingByUser rating);
	
	boolean removeRating(RatingByUser rating);
	
}
