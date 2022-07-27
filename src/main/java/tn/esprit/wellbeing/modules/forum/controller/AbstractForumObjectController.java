package tn.esprit.wellbeing.modules.forum.controller;

import java.util.Collection;
import java.util.List;

import tn.esprit.wellbeing.modules.feedback.comments.Comment;

public interface AbstractForumObjectController<G> {

	G findById(Long id);
	G save(G forumObject);
	Collection<G> findAll();
	void delete(G forumObject);
	void comment(Long forumObjectId,String commentContent);
	void reply(Long forumObjectId,Comment commentToReply,String replyContent);
	List<Comment> getAllComments(Long forumObjectId);
	void suspendComments(Long forumObjctId);
	void activateComments(Long forumObjctId);
	void anonymize(Long id);
	void unAnonymize(Long id);
}
