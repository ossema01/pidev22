package tn.esprit.wellbeing.modules.occurences.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class EventItemProcessor implements ItemProcessor<Event,Event> {
	private static final Logger log = LoggerFactory.getLogger(EventItemProcessor.class);

	  @Override
	  public Event process(final Event event) throws Exception {
		final Long id = event.getId();
	    final String title = event.getTitle().toUpperCase();
	    final String description = event.getDescription().toUpperCase();
	    

	    final Event transformedEvent = new Event(id,title, description);

	    log.info("Converting (" + event + ") into (" + transformedEvent + ")");

	    return transformedEvent;
	  }
}
