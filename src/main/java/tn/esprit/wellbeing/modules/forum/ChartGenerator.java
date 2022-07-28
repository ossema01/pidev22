package tn.esprit.wellbeing.modules.forum;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import tn.esprit.wellbeing.WellBeingApplication;
import tn.esprit.wellbeing.modules.feedback.comments.CommentsService;
import tn.esprit.wellbeing.modules.forum.service.PostService;
import tn.esprit.wellbeing.modules.forum.service.SurveyService;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

public class ChartGenerator {

	static int top;

	public ChartGenerator() {
		top = top != 0 ? top : 3;
	}

	public static void createDatasetSurveys() {
		var dataset = new DefaultPieDataset();
		SurveyService service = WellBeingApplication.context.getBean(SurveyService.class);
		UserService userService = WellBeingApplication.context.getBean(UserService.class);
		Map<String, Integer> mapOfPosts = new HashMap<>();
		userService.getUsers().stream()
			.map(e->e.getUsername())
			.forEach(e->{
				mapOfPosts.put(e, service.findByCreatedBy(e).length);
			});
		mapOfPosts.entrySet().stream()
			.forEach(e->{
				dataset.setValue(e.getKey(), e.getValue());
			});

		JFreeChart chart = ChartFactory.createPieChart("Most survey posted users", dataset, true, false, false);
		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("Most posted users", new Font("Serif", java.awt.Font.BOLD, 18)));
		try {
			ChartUtils.saveChartAsPNG(new File("statsPostedUsers.png"), chart, 450, 400);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void createDatasetPsots() {
		PostService service = WellBeingApplication.context.getBean(PostService.class);
		UserService userService = WellBeingApplication.context.getBean(UserService.class);
		Map<String, Integer> mapOfPosts = new HashMap<>();
		var dataset = new DefaultPieDataset();
		userService.getUsers().stream()
			.map(e->e.getUsername())
			.forEach(e->{
				mapOfPosts.put(e, service.findByCreatedBy(e).length);
			});
		mapOfPosts.entrySet().stream()
			.forEach(e->{
				dataset.setValue(e.getKey(), e.getValue());
			});
		JFreeChart chart = ChartFactory.createPieChart("Posted by users", dataset, true, false, false);
		chart.getLegend().setFrame(BlockBorder.NONE);
		
		
		chart.setTitle(new TextTitle("Users Posts", new Font("Serif", java.awt.Font.BOLD, 18)));
		try {
			ChartUtils.saveChartAsPNG(new File("statsPosts.png"), chart, 450, 400);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void createDatasetComments() {
		var datasetComments = new DefaultPieDataset();
		UserService userService = WellBeingApplication.context.getBean(UserService.class);
		CommentsService service = WellBeingApplication.context.getBean(CommentsService.class);
		Map<String, Integer> mapOfPosts = new HashMap<>();
		userService.getUsers().stream()
		.map(e->e.getUsername())
		.forEach(e->{
			mapOfPosts.put(e, service.findByCreatedBy(e).length);
		});
		mapOfPosts.entrySet().stream()
		.forEach(e->{
			datasetComments.setValue(e.getKey(), e.getValue());
		});

		JFreeChart chartComments = ChartFactory.createPieChart("Active users", datasetComments, true, false, false);

		chartComments.getLegend().setFrame(BlockBorder.NONE);

		chartComments.setTitle(new TextTitle("User Comments", new Font("Serif", java.awt.Font.BOLD, 18)));
		try {
			ChartUtils.saveChartAsPNG(new File("statsCommentes.png"), chartComments, 450, 400);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
