package tn.esprit.wellbeing.modules.forum.models;

import javax.persistence.Entity;

import tn.esprit.wellbeing.modules.forum.AbstractForumObject;

@Entity
public class Article extends AbstractForumObject<String>{

	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
