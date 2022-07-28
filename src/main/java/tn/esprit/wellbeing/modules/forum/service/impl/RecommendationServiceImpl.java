package tn.esprit.wellbeing.modules.forum.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.comments.CommentsService;
import tn.esprit.wellbeing.modules.forum.models.Recommendation;
import tn.esprit.wellbeing.modules.forum.repository.RecommendationReposiory;
import tn.esprit.wellbeing.modules.forum.service.RecommandationService;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationType;
import tn.esprit.wellbeing.modules.notifications.provider.NotificationProviderFactory;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Service
public class RecommendationServiceImpl implements RecommandationService {
	
	@Autowired
	UserService userService;

	@Autowired
	CommentsService commentService;
	
	@Autowired
	RecommendationReposiory repository;

	@Override
	public Recommendation findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Recommendation save(Recommendation forumObject) {
		return repository.save(forumObject);
	}

	@Override
	public Collection<Recommendation> findAll() {
		return (Collection<Recommendation>) repository.findAll();
	}

	@Override
	public void delete(Recommendation forumObject) {
		 repository.delete(forumObject);
	}

	@Override
	public void comment(Long forumObjectId, String commentContent) {
		Recommendation post = findById(forumObjectId);
		if(!post.isSuspendedComments()) {
			Comment coment = new Comment();
			coment.setBody(commentContent);
			post.getComments().add(coment);
			repository.save(post);
			String first = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getFirstName();
			String last = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getLastName();
			Notification notif = NotificationProviderFactory.getDefaultProvider().getNotification("",first+" "+last+" commented on your recommandation", NotificationType.MAIL);
			notif.setToUser(post.getCreatedBy());
			WellBeingApplication.context.getBean(NotificationService.class).sendNotification(notif);
		}	
	}

	@Override
	public void reply(Recommendation entity, Comment commentToReply, String replyContent) {
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
	public void approveRecommandation(Long recommandationId) {
		Recommendation rec = findById(recommandationId);
		User currentUser = userService.getCurrentUser();
		if(!rec.getUsersApprovedRecommandation().contains(currentUser)) {
			if(rec.getUsersRejectedRecommandation().contains(currentUser)) {
				rec.getUsersRejectedRecommandation().remove(currentUser);
			}
			rec.getUsersApprovedRecommandation().add(currentUser);
			repository.save(rec);
			String first = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getFirstName();
			String last = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getLastName();
			Notification notif = NotificationProviderFactory.getDefaultProvider().getNotification("",first+" "+last+" approves your recommandation", NotificationType.MAIL);
			notif.setToUser(rec.getCreatedBy());
			WellBeingApplication.context.getBean(NotificationService.class).sendNotification(notif);
			
		}
		
		
	}

	@Override
	public void rejectRecommandation(Long recommandationId) {
		Recommendation rec = findById(recommandationId);
		User currentUser = userService.getCurrentUser();
		if(!rec.getUsersRejectedRecommandation().contains(currentUser)) {
			if(rec.getUsersApprovedRecommandation().contains(currentUser)) {
				rec.getUsersApprovedRecommandation().remove(currentUser);
			}
			rec.getUsersRejectedRecommandation().add(currentUser);
			repository.save(rec);
			String first = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getFirstName();
			String last = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getLastName();
			Notification notif = NotificationProviderFactory.getDefaultProvider().getNotification("",first+" "+last+" reject your recommandation", NotificationType.MAIL);
			notif.setToUser(rec.getCreatedBy());
			WellBeingApplication.context.getBean(NotificationService.class).sendNotification(notif);
			
			
		}
		
	}

}
