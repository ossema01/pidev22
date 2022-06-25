package tn.esprit.wellbeing.modules.forum;

import tn.esprit.wellbeing.models.SuperEntity;

public class AbstractForumObject<T> extends SuperEntity {
	
	private String title;
	
	private T content;
	
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
