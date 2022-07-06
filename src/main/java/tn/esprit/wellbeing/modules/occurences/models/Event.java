package tn.esprit.wellbeing.modules.occurences.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Event extends AbstractOccurence{
  private boolean needsSubscription ;
	@OneToMany
	private Collection<Subscription> subscriptionList = new ArrayList<>();
	public boolean isNeedsSubscription() {
		return needsSubscription;
	}
	public void setNeedsSubscription(boolean needsSubscription) {
		this.needsSubscription = needsSubscription;
	}
	public Collection<Subscription> getSubscriptionList() {
		return subscriptionList;
	}
	public void setSubscriptionList(Collection<Subscription> subscriptionList) {
		this.subscriptionList = subscriptionList;
	}
	
}
