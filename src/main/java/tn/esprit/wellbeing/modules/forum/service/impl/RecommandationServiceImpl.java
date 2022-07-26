package tn.esprit.wellbeing.modules.forum.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.models.SuperEntity;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.comments.CommentsService;
import tn.esprit.wellbeing.modules.forum.exception.ObjectNotFoundException;
import tn.esprit.wellbeing.modules.forum.models.Recommendation;
import tn.esprit.wellbeing.modules.forum.repository.RecommendationReposiory;
import tn.esprit.wellbeing.modules.forum.service.RecommandationService;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Service
public class RecommandationServiceImpl implements RecommandationService {

	private static final ApplicationContext context = WellBeingApplication.context;

	@Autowired
	RecommendationReposiory repository;

	@Autowired
	CommentsService commentService;

	@Autowired
	UserService userService;

	@Override
	public Recommendation findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}

	@Override
	public Collection<Recommendation> findAll() {
		return (Collection<Recommendation>) repository.findAll();
	}

	@Override
	public Recommendation save(Recommendation object) {
		return repository.save(object);
	}

	@Override
	public void delete(Recommendation forumObject) {
		repository.delete(forumObject);
	}

	@Override
	public void comment(Recommendation forumObject, String commentContent) {
		commentService.addComment(forumObject, commentContent);
		save(forumObject);
		forumObject.getTaggedUsers().stream().filter(e -> !forumObject.getDeactivatedNotificationUsers().contains(e))
				.forEach(e -> {
					context.getBean(NotificationService.class).sendNotification(
							((SuperEntity) forumObject).getCreatedBy(),
							context.getBean(UserService.class).getCurrentUser()
									+ " commented on your post or a post where you have been tagged");

				});
	}

	@Override
	public void reply(Recommendation commentToReply, Comment comment, String replyContent) {
		commentService.addReply(commentToReply, comment, replyContent);
		save(commentToReply);
	}

	@Override
	public List<Comment> getAllComments(Long forumObjectId) {
		return findById(forumObjectId).getComments();
	}

	@Override
	public void suspendComments(Recommendation forumObjctId) {
		if (userService.getCurrentUser().equals(forumObjctId.getCreatedBy())) {
			repository.suspendComments(forumObjctId.getId());
		}
	}

	@Override
	public void activateComments(Recommendation forumObjctId) {
		if (userService.getCurrentUser().equals(forumObjctId.getCreatedBy())) {
			repository.activateComments(forumObjctId.getId());
		}
	}

	@Override
	public void anonymize(Recommendation post) {
		if (userService.getCurrentUser().equals(post.getCreatedBy())) {
			repository.anonymize(post.getId());
		}

	}

	@Override
	public void unAnonymize(Recommendation post) {
		if (userService.getCurrentUser().equals(post.getCreatedBy())) {
			repository.unAnonymize(post.getId());
		}

	}

	@Override
	public void suspendNotification(Recommendation post) {
		List<User> users = post.getDeactivatedNotificationUsers();
		users.add(userService.getCurrentUser());
		post.setDeactivatedNotificationUsers(users);
		save(post);
	}

	@Override
	public void activateNotification(Recommendation post) {
		List<User> users = post.getDeactivatedNotificationUsers();
		users.remove(userService.getCurrentUser());
		post.setDeactivatedNotificationUsers(users);
		save(post);
	}

}
