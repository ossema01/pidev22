package tn.esprit.wellbeing.modules.feedback.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	UserService userService;

	@Override
	public void addComment(HasComments entity, String commentBody) {
		if (entity == null || commentBody == null || commentBody.isEmpty()) {
			return;
		}
		Comment comment = new Comment();
		comment.setBody(commentBody);
		entity.addComment(comment);
	}

	@Override
	public void addReply(HasComments entity, Comment comment, String replyBody) {
		if (entity == null || comment == null || replyBody == null || replyBody.isEmpty()) {
			return;
		}
		Comment reply = new Comment();
		reply.setBody(replyBody);
		comment.addReply(reply);		
		notificationService.sendNotification(comment.getCreatedBy(),userService.getCurrentUser()+" replied to your comment");
	}

}
