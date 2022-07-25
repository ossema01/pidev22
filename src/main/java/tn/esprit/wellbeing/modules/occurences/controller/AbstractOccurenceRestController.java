package tn.esprit.wellbeing.modules.occurences.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.occurences.models.AbstractOccurence;
import tn.esprit.wellbeing.modules.occurences.services.IAbstractOccurenceService;

//AbstractOccurenceRestController
@RestController // = @Controller + @ResponseBody 
public class AbstractOccurenceRestController {
		@Autowired
		IAbstractOccurenceService abstractOccurenceService;

		// http://localhost:8090/pidev/retrieve-all-abstractOccurences
		@GetMapping("/retrieve-all-abstractOccurences")
		public List<AbstractOccurence> retrieveAllAbstractOccurences() {
			List<AbstractOccurence> list = abstractOccurenceService.retrieveAllAbstractOccurences();
			return list;
		}

		// http://localhost:8090/pidev/retrieve-abstractOccurence/{abstractOccurence-id}
		@GetMapping("/retrieve-abstractOccurence/{abstractOccurence-id}")
		public AbstractOccurence retrieveAbstractOccurence(@PathVariable("abstractOccurence-id") Long abstractOccId) {
			return abstractOccurenceService.retrieveAbstractOccurence(abstractOccId);
		}

		// http://localhost:8090/pidev/create-abstractOccurence
		@PostMapping("/create-abstractOccurence")
		public AbstractOccurence createAbstractOccurence(@RequestBody AbstractOccurence abstractOcc) {
			AbstractOccurence abstractOccurence = abstractOccurenceService.createAbstractOccurence(abstractOcc);
			return abstractOccurence;
		}

		// http://localhost:8090/pidev/remove-abstractOccurence/{abstractOccurence-id}
		@DeleteMapping("/remove-abstractOccurence/{abstractOccurence-id}")
		public void deleteAbstractOccurence(@PathVariable("abstractOccurence-id") Long abstractOccId) {
			abstractOccurenceService.deleteAbstractOccurence(abstractOccId);
		}

		// http://localhost:8090/pidev/modify-abstractOccurence
		@PutMapping("/modify-abstractOccurence")
		public AbstractOccurence updateAbstractOccurencet(@RequestBody AbstractOccurence abstractOcc) {
			return abstractOccurenceService.updateAbstractOccurencet(abstractOcc);
		}	
}
