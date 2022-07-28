package tn.esprit.wellbeing.modules.forum.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tn.esprit.wellbeing.modules.forum.AbstractForumObject;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

@Entity
public class Recommendation extends AbstractForumObject {

	private String recommendedSubject;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> usersApprovedRecommandation = new ArrayList<>();
	
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> usersRejectedRecommandation = new ArrayList<>();
	

	public String getRecommendedSubject() {
		return recommendedSubject;
	}

	public void setRecommendedSubject(String recommendedSubject) {
		this.recommendedSubject = recommendedSubject;
	}

	public List<User> getUsersApprovedRecommandation() {
		return usersApprovedRecommandation;
	}

	public void setUsersApprovedRecommandation(List<User> usersApprovedRecommandation) {
		this.usersApprovedRecommandation = usersApprovedRecommandation;
	}

	public List<User> getUsersRejectedRecommandation() {
		return usersRejectedRecommandation;
	}

	public void setUsersRejectedRecommandation(List<User> usersRejectedRecommandation) {
		this.usersRejectedRecommandation = usersRejectedRecommandation;
	}
}
