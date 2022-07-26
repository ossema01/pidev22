package tn.esprit.wellbeing.modules.forum.models;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.modules.forum.AbstractForumObject;
import tn.esprit.wellbeing.modules.notifications.NotificationService;

@Entity
public class Recommendation extends AbstractForumObject<String> {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> recommandedUsers = new ArrayList<>();
	
	private String description;
	
	public List<User> getRecommandedUsers() {
		return recommandedUsers;
	}

	public void setRecommandedUsers(List<User> recommandedUsers) {
		this.recommandedUsers = recommandedUsers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@PostPersist
	public void postPersist() {
		String msg = "A new recommandation is added for you!";
		Object params[] = null;
		params[0] = msg;
		params[2] = this;
		recommandedUsers.forEach(e->{
			params[1] = e.getUsername();
			WellBeingApplication.context.getBean(NotificationService.class).sendNotification(params);
		});
		
	}
	
	@PostUpdate
	public void postUpdate() {
		String msg = "An update was made on recommandation !";
		Object params[] = null;
		params[0] = msg;
		params[2] = this;
		recommandedUsers.forEach(e->{
			params[1] = e.getUsername();
			WellBeingApplication.context.getBean(NotificationService.class).sendNotification(params);
		});
	}
	
}
