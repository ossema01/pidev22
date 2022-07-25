package tn.esprit.wellbeing.modules.forum.service.impl;

import java.util.List;

import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.forum.service.AbstractForumObjectService;

public abstract class AbstractForumObjectServiceImpl<G> implements AbstractForumObjectService<G> {

	@Override
	public void comment(Long forumObjectId, String commentContent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reply(Long commentToReplyId, String replyContent) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comment> getAllComments(Long forumObjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void suspendComments(Long forumObjctId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activateComments(Long forumObjctId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void anonymize(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unAnonymize(Long id) {
		// TODO Auto-generated method stub

	}

}
