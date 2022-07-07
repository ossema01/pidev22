package tn.esprit.wellbeing.modules.occurences.models;

import javax.persistence.Entity;

@Entity
public class Activity extends AbstractOccurence{
	

	public Activity(Long id , String title, String description) {
		super(id, title, description);
		// TODO Auto-generated constructor stub
	}

}
