package tn.esprit.wellbeing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.esprit.wellbeing.modules.occurences.downloadPDF.PDFGenerator;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class WellBeingApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(WellBeingApplication.class, args);
    //    PDFGenerator pDFGenerator = ac.getBean("pdfGenerator",PDFGenerator.class);
		
	//	pDFGenerator.generatePdfReport();

	}

}
