package tn.esprit.wellbeing.modules.forum.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.forum.models.Survey;
import tn.esprit.wellbeing.modules.forum.service.SurveyService;

@RestController()
@RequestMapping("survey")
public class SurveyController implements AbstractForumObjectController<Survey> {

	@Autowired
	SurveyService service;

	@GetMapping("/{id}")
	public Survey findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping()
	public Collection<Survey> findAll() {
		return service.findAll();
	}
	
	@PostMapping()
	public Survey save(@RequestBody Survey object) {
		return service.save(object);
	}

	@Override
	@DeleteMapping()
	public void delete(@RequestBody Survey forumObject) {
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
		Survey post = findById(forumObjectId);
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
	
	@PutMapping("/{id}/question/{quesion}")
	public void addQuetion(@PathVariable("id") Long surveyId,@PathVariable("quesion")String question) {
		service.addSurveyQuestion(surveyId, question);
	}
	
	@PutMapping("/{id}/response/{response}")
	public void addResponse(@PathVariable("id") Long questionId,@PathVariable("response")String response) {
		service.respond(questionId, response);
	}
	
}