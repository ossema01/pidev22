package tn.esprit.wellbeing.feedback.ratings;

import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

	public void addRating(HasRating entity, float rating) {
		if (entity == null) {
			return;
		}

		RatingByUser ratingByUser = new RatingByUser();
		ratingByUser.setRate(rating);
		entity.addRating(ratingByUser);
	}

	@Override
	public Rating calculateRating(HasRating entity) {
		Double avg = entity.getRatings().stream().mapToDouble(r -> r.getRate()).average().getAsDouble();
		Rating rate = new Rating(avg.floatValue(), entity.getRatings().size());
		return rate;
	}

}
