package tn.esprit.wellbeing.modules.forum.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.forum.models.Post;
import tn.esprit.wellbeing.modules.forum.service.PostService;

@RestController()
@RequestMapping("post")
public class PostController implements AbstractForumObjectController<Post>{
	
	@Autowired
	PostService service;
	
	
	
	@GetMapping("/{id}")
	public Post findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping()
	public Collection<Post> findAll() {
		return service.findAll();
	}
	
	@PostMapping()
	public Post save(@RequestBody Post object) {
		return service.save(object);
	}

	@Override
	@DeleteMapping()
	public void delete(@RequestBody Post forumObject) {
		service.delete(forumObject);
		
	}

	@Override
	@PostMapping("/{id}/comment/{commentContent}")
	public void comment(@PathVariable("id") Long forumObjectId,@PathVariable("commentContent") String commentContent) {
		service.comment(forumObjectId, commentContent);
		
	}

	@Override
	@PostMapping("/{id}/reply/{replyContent}")
	public void reply(@PathVariable("id") Long forumObjectId,@RequestBody Comment commentToReply,@PathVariable("replyContent") String replyContent) {
		Post post = findById(forumObjectId);
		service.reply(post, commentToReply, replyContent);		
	}

	@Override
	@GetMapping("/{id}/comment/all")
	public List<Comment> getAllComments(@PathVariable("id") Long forumObjectId) {
		return service.getAllComments(forumObjectId);
	}

	@Override
	@GetMapping("/{id}/comment/suspend")
	public void suspendComments(@PathVariable("id") Long forumObjctId) {
		service.suspendComments(forumObjctId);
	}

	@Override
	@GetMapping("/{id}/comment/activate")
	public void activateComments(@PathVariable("id") Long forumObjctId) {
		service.activateComments(forumObjctId);
		
	}

	@Override
	@GetMapping("/{id}/anonymize")
	public void anonymize(Long id) {
		service.anonymize(id);
		
	}

	@Override
	@GetMapping("/{id}/unAnonymize")
	public void unAnonymize(Long id) {
		service.unAnonymize(id);
		
	}
	
	

}
