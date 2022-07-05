package tn.esprit.wellbeing.modules.occurences.models;

import javax.persistence.Entity;

import tn.esprit.wellbeing.models.SuperEntity;
@Entity
public class FavoriteByUser extends SuperEntity {
	private String subjectToFavorite;

	public String getSubjectToFavorite() {
		return subjectToFavorite;
	}

	public void setSubjectToFavorite(String subjectToFavorite) {
		this.subjectToFavorite = subjectToFavorite;
	}

}
