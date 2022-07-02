package tn.esprit.wellbeing.modules.occurences.models;

import javax.persistence.Entity;

@Entity
public class Event extends AbstractOccurence{
  private boolean needsSubscription ;
}
