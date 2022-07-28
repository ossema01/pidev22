package tn.esprit.wellbeing.modules.forum.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.comments.CommentsService;
import tn.esprit.wellbeing.modules.forum.models.Post;
import tn.esprit.wellbeing.modules.forum.models.Survey;
import tn.esprit.wellbeing.modules.forum.models.SurveyQuestion;
import tn.esprit.wellbeing.modules.forum.models.SurveyResponse;
import tn.esprit.wellbeing.modules.forum.repository.SurveyQuestionRepository;
import tn.esprit.wellbeing.modules.forum.repository.SurveyRepository;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationType;
import tn.esprit.wellbeing.modules.notifications.provider.NotificationProviderFactory;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Service
public class SurveyService implements tn.esprit.wellbeing.modules.forum.service.SurveyService {

	@Autowired
	CommentsService commentService;
	
	@Autowired
	 SurveyRepository repository;
	
	@Autowired
	 SurveyQuestionRepository responseQuest;

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
		if(!post.isSuspendedComments()) {
			Comment coment = new Comment();
			coment.setBody(commentContent);
			post.getComments().add(coment);
			repository.save(post);
			String first = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getFirstName();
			String last = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getLastName();
			Notification notif = NotificationProviderFactory.getDefaultProvider().getNotification("",first+" "+last+" commented on your survey", NotificationType.MAIL);
			notif.setToUser(post.getCreatedBy());
			WellBeingApplication.context.getBean(NotificationService.class).sendNotification(notif);
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

	@Override
	public void addSurveyQuestion(Long surveyId, String question) {
		Survey survey = findById(surveyId);
		SurveyQuestion quest = new SurveyQuestion();
		quest.setQuestion(question);
		survey.getQuestions().add(quest);
		repository.save(survey);
	}

	@Override
	public void respond(Long questionId, String response) {
		SurveyQuestion question = responseQuest.findById(questionId).get();
		SurveyResponse resp = new SurveyResponse();
		resp.setResponse(response);
		question.getResponses().add(resp);
		responseQuest.save(question);
		
		String first = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getFirstName();
		String last = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getLastName();
		Notification notif = NotificationProviderFactory.getDefaultProvider().getNotification("",first+" "+last+" gave a response for your question", NotificationType.MAIL);
		notif.setToUser(question.getCreatedBy());
		WellBeingApplication.context.getBean(NotificationService.class).sendNotification(notif);
	}

	@Override
	public Survey[] findByCreatedBy(String username) {
		return repository.findAllByCreatedBy(username);
	}

}
