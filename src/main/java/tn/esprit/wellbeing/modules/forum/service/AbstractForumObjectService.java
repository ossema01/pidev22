package tn.esprit.wellbeing.modules.forum.service;

import java.util.Collection;
import java.util.List;

import tn.esprit.wellbeing.modules.feedback.comments.Comment;

public interface AbstractForumObjectService<G> {
	
	G findById(Long id);
	G save(G forumObject);
	Collection<G> findAll();
	void delete(G forumObject);
	void comment(G forumObject,String commentContent);
	void reply(G commentToReply,Comment comment, String replyContent);
	List<Comment> getAllComments(Long forumObjectId);
	void suspendComments(G forumObjctId);
	void activateComments(G forumObjctId);
	void anonymize(G id);
	void unAnonymize(G id);
	void suspendNotification(G id);
	void activateNotification(G id);
}
