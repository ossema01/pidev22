package tn.esprit.wellbeing.modules.collaborations.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.collaborations.ExcelExporter.OfferExcelExporter;
import tn.esprit.wellbeing.modules.collaborations.models.Offre;
import tn.esprit.wellbeing.modules.collaborations.services.IOffreService;

@RestController
public class OffreRestController {

	@Autowired
	IOffreService offreService;

	// http://localhost:8090/pidev/retrieve-all-offers
	@GetMapping("/retrieve-all-offers")
	public List<Offre> retrieveAllOffres() {
		List<Offre> list = offreService.retrieveAllOffers();
		return list;
	}

	// http://localhost:8090/pidev/retrieve-offer/{offer-id}
	@GetMapping("/retrieve-offer/{offre-id}")
	public Offre retrieveOffre(@PathVariable("offre-id") Long offerId) {
		return offreService.retrieveOffre(offerId);
	}

	// http://localhost:8090/pidev/add-offer
	@PostMapping("/add-offer")
	public Offre addOffre(@RequestBody Offre offer) {
		Offre offre = offreService.addOffre(offer);
		return offre;
	}

	// http://localhost:8090/pidev/remove-offer/{offer-id}
	@DeleteMapping("/remove-offer/{offre-id}")
	public void removeOffre(@PathVariable("offre-id") Long offerId) {
		offreService.deleteOffre(offerId);
	}

	// http://localhost:8090/pidev/modify-offer
	@PutMapping("/modify-offer")
	public Offre updateOffre(@RequestBody Offre offer) {
		return offreService.updateOffre(offer);
	}
	
	// http://localhost:8090/pidev/add-rating-to-offer
	@PostMapping("/add-rating")
		public void addRating(@RequestParam("offre-id") Long offerId, @RequestParam ("rating") float rating ) {
			offreService.addRating(offerId, rating);
		
		}
		
	// http://localhost:8090/pidev/export/offers/excel
	@GetMapping("/export/offers/excel")
	    public void exportToExcel(HttpServletResponse response, @RequestParam(name = "dateBefore", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateBefore,
	            @RequestParam(name = "dateAfter", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dateAfter) throws IOException {
		
		
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=offers_" + currentDateTime + ".csv";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Offre> offersList = offreService.exportAllOffersByDate(dateBefore, dateAfter);
	        
	         
	       OfferExcelExporter excelExporter = new OfferExcelExporter(offersList);
	         
	        excelExporter.export(response);
	    }

}
