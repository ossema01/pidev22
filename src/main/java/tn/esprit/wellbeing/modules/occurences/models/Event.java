package tn.esprit.wellbeing.modules.occurences.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event extends AbstractOccurence{
 //   @Column(name="needsSubscription")
 //   private boolean needsSubscription ;
	@OneToMany
	private Collection<Subscription> subscriptionList = new ArrayList<>();
	
	@OneToMany
	private Collection<Activity> activitiesList = new ArrayList<>();


/*	public boolean isNeedsSubscription() {
		return needsSubscription;
	}
	public void setNeedsSubscription(boolean needsSubscription) {
		this.needsSubscription = needsSubscription;
	}*/
	public Collection<Subscription> getSubscriptionList() {
		return subscriptionList;
	}
	public Collection<Activity> getActivitiesList() {
		return activitiesList;
	}
	public void setActivitiesList(Collection<Activity> activitiesList) {
		this.activitiesList = activitiesList;
	}
	public void setSubscriptionList(Collection<Subscription> subscriptionList) {
		this.subscriptionList = subscriptionList;
	}
	  public Event() {
			super();
			// TODO Auto-generated constructor stub
		}
	  public Event(Long id, String title, String description) {
			super(id,title, description);
			// TODO Auto-generated constructor stub
		}
	
}
