package tn.esprit.wellbeing.modules.feedback.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Autowired
	CommentsRepository commentRepo;

	@Override
	public void addComment(HasComments entity, String commentBody) {
		if (entity == null || commentBody == null || commentBody.isEmpty()) {
			return;
		}
		Comment comment = new Comment();
		comment.setBody(commentBody);
		entity.addComment(comment);
		commentRepo.save(comment);
		
	}

	@Override
	public void addReply(HasComments entity, Comment comment, String replyBody) {
		if (entity == null || comment == null || replyBody == null || replyBody.isEmpty()) {
			return;
		}
		Comment reply = new Comment();
		reply.setBody(replyBody);
		comment.addReply(reply);
		commentRepo.save(comment);
	}

	@Override
	public Comment[] findByCreatedBy(String e) {
		return commentRepo.findAllByCreatedBy(e);
	}

}
