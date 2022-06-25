package tn.esprit.wellbeing.feedback.ratings;

public interface RatingService {
	
	void addRating(HasRating entity , float rating);
	
	Rating calculateRating(HasRating entity);
	
}
