package tn.esprit.wellbeing.modules.forum.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.feedback.comments.CommentsService;
import tn.esprit.wellbeing.modules.forum.exception.ObjectNotFoundException;
import tn.esprit.wellbeing.modules.forum.models.Post;
import tn.esprit.wellbeing.modules.forum.repository.PostRepository;
import tn.esprit.wellbeing.modules.forum.service.PostService;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationType;
import tn.esprit.wellbeing.modules.notifications.provider.NotificationProviderFactory;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	CommentsService commentService;

	@Autowired
	PostRepository repository;

	@Override
	public Post findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}

	@Override
	public Collection<Post> findAll() {
		return (Collection<Post>) repository.findAll();
	}

	@Override
	public Post save(Post object) {
		return repository.save(object);
	}

	@Override
	public void delete(Post forumObject) {
		repository.delete(forumObject);
	}

	@Override
	public Post[] findByCreatedBy(String username) {
		return repository.findAllByCreatedBy(username);
	}

	@Override
	public void comment(Long forumObjectId, String commentContent) {
		Post post = findById(forumObjectId);
		if(!post.isSuspendedComments()) {
			Comment coment = new Comment();
			coment.setBody(commentContent);
			post.getComments().add(coment);
			repository.save(post);
			String first = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getFirstName();
			String last = WellBeingApplication.context.getBean(UserService.class).getCurrentUser().getLastName();
			Notification notif = NotificationProviderFactory.getDefaultProvider().getNotification("",first+" "+last+" commented on your post", NotificationType.MAIL);
			notif.setToUser(post.getCreatedBy());
			WellBeingApplication.context.getBean(NotificationService.class).sendNotification(notif);
		}		
	}

	@Override
	public void reply(Post post,Comment commentToReply, String replyContent) {
		commentService.addReply(post, commentToReply, replyContent);
		repository.save(post);
		
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
