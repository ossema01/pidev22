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
import tn.esprit.wellbeing.modules.forum.service.PostService;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

public class ChartGenerator {

	static int top;

	public ChartGenerator() {
		top = top != 0 ? top : 3;
	}

	public static void createDatasetSurveys() {
		var dataset = new DefaultPieDataset();

		JFreeChart chart = ChartFactory.createPieChart("Most posted users", dataset, true, false, false);
		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("ost posted users", new Font("Serif", java.awt.Font.BOLD, 18)));
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
		
		
		chart.setTitle(new TextTitle("ost posted users", new Font("Serif", java.awt.Font.BOLD, 18)));
		try {
			ChartUtils.saveChartAsPNG(new File("statsPosts.png"), chart, 450, 400);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void createDatasetComments() {
		var datasetComments = new DefaultCategoryDataset();
		PostService service = WellBeingApplication.context.getBean(PostService.class);
		service.findAll().stream()
				.sorted((x, y) -> Integer.valueOf(x.getComments().size()).compareTo(y.getComments().size())).limit(top)
				.forEach(e -> {
					datasetComments.setValue(e.getComments().size(), "Number of comments", e.getCreatedBy());
				});

		JFreeChart chartComments = ChartFactory.createBarChart("Active Posts", "User", "Posts", datasetComments,
				PlotOrientation.VERTICAL, true, true, false);

		chartComments.getLegend().setFrame(BlockBorder.NONE);

		chartComments.setTitle(new TextTitle("Most commented posts", new Font("Serif", java.awt.Font.BOLD, 18)));
		try {
			ChartUtils.saveChartAsPNG(new File("statsCommentes.png"), chartComments, 450, 400);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private JFreeChart createChart(CategoryDataset dataset) {

		JFreeChart chart = ChartFactory.createBarChart("Active Users", "User", "Posts", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(new TextTitle("Most commented posts", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;
	}
}
