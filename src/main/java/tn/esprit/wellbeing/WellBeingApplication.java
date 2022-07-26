package tn.esprit.wellbeing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.esprit.wellbeing.modules.occurences.downloadPDF.PDFGenerator;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class WellBeingApplication {
	
	public static ApplicationContext context;

	public static void main(String[] args) {		

		context = SpringApplication.run(WellBeingApplication.class, args);
	}

	@Configuration
	public class MyWebMvcConfig {

		@Bean
		public WebMvcConfigurer forwardToIndex() {
			return new WebMvcConfigurer() {
				@Override
				public void addResourceHandlers(ResourceHandlerRegistry registry) {
					registry.addResourceHandler("/**").addResourceLocations("classpath:/public/");
				}
			};
		}

	}

	@Configuration
	public class WebAdapterConfig implements WebMvcConfigurer {

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/websockettest").setViewName("forward:/index.html");
		}
	}

}
