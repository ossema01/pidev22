package tn.esprit.wellbeing.modules.forum.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.forum.AbstractForumObject;
import tn.esprit.wellbeing.modules.forum.models.Post;
import tn.esprit.wellbeing.modules.forum.service.PostService;

@RestController()
@RequestMapping("post")
public class PostController {
	
	@Autowired
	PostService service;
	
	@GetMapping("/{id}")
	public AbstractForumObject<String> findById(@PathVariable Long id) {
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
	
	

}
