package tn.esprit.wellbeing.modules.occurences.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class ActivityItemProcessor implements ItemProcessor<Activity,Activity> {
	private static final Logger log = LoggerFactory.getLogger(ActivityItemProcessor.class);

	  @Override
	  public Activity process(final Activity activity) throws Exception {
		final Long id = activity.getId();
	    final String title = activity.getTitle().toUpperCase();
	    final String description = activity.getDescription().toUpperCase();
	    

	    final Activity transformedActivity = new Activity(id,title, description);

	    log.info("Converting (" + activity + ") into (" + transformedActivity + ")");

	    return transformedActivity;
	  }
}
