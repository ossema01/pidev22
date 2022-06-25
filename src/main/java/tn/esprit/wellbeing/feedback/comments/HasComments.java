package tn.esprit.wellbeing.feedback.comments;

import java.util.List;

public interface HasComments {
	
	List<Comment> getComments();
	
	void addComment(Comment comment);
	
	boolean removeComment(Comment comment);
	
}
