package tn.esprit.wellbeing.modules.evaluation.models;

import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.user.entity.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Badge extends SuperEntity {
	
	private String title;
	
	private String description;
	
	private int pointsNbrToGetBadge;
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPointsNbrToGetBadge() {
		return pointsNbrToGetBadge;
	}

	public void setPointsNbrToGetBadge(int pointsNbrToGetBadge) {
		this.pointsNbrToGetBadge = pointsNbrToGetBadge;
	}
	
	
	

}
