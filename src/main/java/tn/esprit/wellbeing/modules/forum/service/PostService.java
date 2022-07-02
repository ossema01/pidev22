package tn.esprit.wellbeing.modules.forum.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.forum.exception.ObjectNotFoundException;
import tn.esprit.wellbeing.modules.forum.models.Post;
import tn.esprit.wellbeing.modules.forum.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	PostRepository repository;

	public Post findById(Long id){
		return repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id));
	}
	
	public Collection<Post> findAll() {
		return (Collection<Post>) repository.findAll();
	}
	
	public Post save(Post object) {
		return repository.save(object);
	}
}
