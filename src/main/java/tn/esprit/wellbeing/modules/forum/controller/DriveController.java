package tn.esprit.wellbeing.modules.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.feedback.comments.Comment;
import tn.esprit.wellbeing.modules.forum.ChartGenerator;
import tn.esprit.wellbeing.modules.forum.DriveUploader;
import tn.esprit.wellbeing.modules.forum.models.Post;
import tn.esprit.wellbeing.modules.forum.repository.PostRepository;


@RestController()
@RequestMapping("drive")
public class DriveController {
	
	@Autowired
	PostRepository repoPost;
	
	@GetMapping()
	public void doDrive() {
		Post post1 = new Post();
		post1.setCreatedBy("ossemaa");
		Post post2 = new Post();
		post2.setCreatedBy("rouaaa");
		Post post3 = new Post();
		post3.setCreatedBy("nouhailaaa");
		Comment comment1 = new Comment();
		Comment comment2 = new Comment();
		Comment comment3 = new Comment();
		post1.addComment(comment1);
		post3.addComment(comment2);
		post3.addComment(comment3);
		repoPost.save(post1);
		repoPost.save(post2);
		repoPost.save(post3);
		ChartGenerator.createDatasetComments();
		ChartGenerator.createDatasetPsots();
		DriveUploader.uploadStatisticsPostsFile();
	}

}
