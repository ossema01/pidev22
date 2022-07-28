package tn.esprit.wellbeing.modules.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.forum.ChartGenerator;
import tn.esprit.wellbeing.modules.forum.DriveUploader;
import tn.esprit.wellbeing.modules.forum.repository.PostRepository;


@RestController()
@RequestMapping("drive")
public class DriveController {
	
	@Autowired
	PostRepository repoPost;
	
	@GetMapping()
	public void doDrive() {
		ChartGenerator.createDatasetComments();
		ChartGenerator.createDatasetPsots();
		ChartGenerator.createDatasetSurveys();
		DriveUploader.uploadStatisticsCommentsFile();
		DriveUploader.uploadStatisticsPostsFile();
	}

}
