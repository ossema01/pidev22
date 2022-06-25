package tn.esprit.wellbeing.modules.feedback.comments;

public interface CommentsService {

	void addComment(HasComments entity, String commentBody);

	void addReply(HasComments entity, Comment comment, String replyBody);
}
