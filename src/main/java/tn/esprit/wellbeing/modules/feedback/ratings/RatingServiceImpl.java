package tn.esprit.wellbeing.modules.feedback.ratings;

import java.util.OptionalDouble;

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
		OptionalDouble avg = entity.getRatings().stream().mapToDouble(r -> r.getRate()).average();
		if (avg.isEmpty()) {
			return new Rating(0, entity.getRatings().size());
		}
		Rating rate = new Rating(((Double) avg.getAsDouble()).floatValue(), entity.getRatings().size());
		return rate;
	}

}
