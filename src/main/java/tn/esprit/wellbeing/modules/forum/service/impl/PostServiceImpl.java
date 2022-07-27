package tn.esprit.wellbeing.modules.forum.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.wellbeing.modules.forum.exception.ObjectNotFoundException;
import tn.esprit.wellbeing.modules.forum.models.Post;
import tn.esprit.wellbeing.modules.forum.repository.PostRepository;
import tn.esprit.wellbeing.modules.forum.service.PostService;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

@Service
public class PostServiceImpl extends AbstractForumObjectServiceImpl<Post> implements PostService {

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
	public User[] findByCreatedBy(String username) {
		return repository.findAllByCreatedBy(username);
	}
}
