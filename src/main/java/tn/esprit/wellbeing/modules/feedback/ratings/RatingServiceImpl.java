package tn.esprit.wellbeing.modules.feedback.ratings;

import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired 
	UserService userService;

	public void addRating(HasRating entity, float rating) {
		if (entity == null) {
			return;
		}
		RatingByUser ratingByUser = null;
		Optional< RatingByUser> ratingByUserOptional = entity.getRatings().stream().filter(r -> r.getCreatedBy().equals(userService.getCurrentUser().getUsername())).findFirst();
		if (ratingByUserOptional.isPresent()) {
			ratingByUser = ratingByUserOptional.get();
			entity.getRatings().remove(ratingByUser);
		}else {
			ratingByUser = new RatingByUser();
		}
	
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
