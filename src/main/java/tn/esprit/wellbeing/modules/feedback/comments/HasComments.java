package tn.esprit.wellbeing.modules.feedback.comments;

import java.util.List;

public interface HasComments {
	
	List<Comment> getComments();
	
	void addComment(Comment comment);
	
	boolean removeComment(Comment comment);
	
}
