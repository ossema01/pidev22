package tn.esprit.wellbeing.modules.forum.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.comments.CommentsService;
import tn.esprit.wellbeing.modules.forum.models.Survey;
import tn.esprit.wellbeing.modules.forum.repository.SurveyRepository;

public class SurveyService implements tn.esprit.wellbeing.modules.forum.service.SurveyService {

	@Autowired
	CommentsService commentService;
	
	@Autowired
	 SurveyRepository repository;

	@Override
	public  Survey findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Survey save(Survey forumObject) {
		return repository.save(forumObject);
	}

	@Override
	public Collection<Survey> findAll() {
		return (Collection<Survey>) repository.findAll();
	}

	@Override
	public void delete(Survey forumObject) {
		 repository.delete(forumObject);
	}

	@Override
	public void comment(Long forumObjectId, String commentContent) {
		Survey post = findById(forumObjectId);
		if(post.isSuspendedComments()) {
			Comment comment = new Comment();
			comment.setBody(commentContent);
			
			post.getComments().add(comment);
			
			repository.save(post);
		}		
	}

	@Override
	public void reply(Survey entity, Comment commentToReply, String replyContent) {
		commentService.addReply(entity, commentToReply, replyContent);
		repository.save(entity);
	}

	@Override
	public List<Comment> getAllComments(Long forumObjectId) {
		return findById(forumObjectId).getComments();
	}

	@Override
	public void suspendComments(Long forumObjctId) {
		repository.suspendComments(forumObjctId);
		
	}

	@Override
	public void activateComments(Long forumObjctId) {
		repository.activateComments(forumObjctId);
		
	}

	@Override
	public void anonymize(Long id) {
		repository.anonymize(id);
		
	}

	@Override
	public void unAnonymize(Long id) {
		repository.unAnonymize(id);
		
	}

}
