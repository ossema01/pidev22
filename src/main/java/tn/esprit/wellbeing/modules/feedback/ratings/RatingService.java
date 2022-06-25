package tn.esprit.wellbeing.modules.feedback.ratings;

public interface RatingService {
	
	void addRating(HasRating entity , float rating);
	
	Rating calculateRating(HasRating entity);
	
}
