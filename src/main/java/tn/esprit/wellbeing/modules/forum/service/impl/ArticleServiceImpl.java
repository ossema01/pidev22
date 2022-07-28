package tn.esprit.wellbeing.modules.forum.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.comments.CommentsService;
import tn.esprit.wellbeing.modules.forum.models.Article;
import tn.esprit.wellbeing.modules.forum.repository.ArticleRepository;
import tn.esprit.wellbeing.modules.forum.service.ArticleService;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationType;
import tn.esprit.wellbeing.modules.notifications.provider.NotificationProviderFactory;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	CommentsService commentService;
	
	@Autowired
	ArticleRepository repository;

	@Override
	public Article findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Article save(Article forumObject) {
		return repository.save(forumObject);
	}

	@Override
	public Collection<Article> findAll() {
		return (Collection<Article>) repository.findAll();
	}

	@Override
	public void delete(Article forumObject) {
		 repository.delete(forumObject);
	}

	@Override
	public void comment(Long forumObjectId, String commentContent) {
		Article post = findById(forumObjectId);
		if(!post.isSuspendedComments()) {
			Comment coment = new Comment();
			coment.setBody(commentContent);
			post.getComments().add(coment);
			repository.save(post);
			String first = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getFirstName();
			String last = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getLastName();
			Notification notif = NotificationProviderFactory.getDefaultProvider().getNotification("",first+" "+last+" commented on your article", NotificationType.MAIL);
			notif.setToUser(post.getCreatedBy());
			WellBeingApplication.context.getBean(NotificationService.class).sendNotification(notif);
		}	
	}

	@Override
	public void reply(Article entity, Comment commentToReply, String replyContent) {
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
