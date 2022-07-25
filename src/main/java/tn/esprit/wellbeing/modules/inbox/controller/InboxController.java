package tn.esprit.wellbeing.modules.inbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.inbox.InboxService;
import tn.esprit.wellbeing.modules.inbox.data.Message;

@RestController
@RequestMapping("/inbox")
public class InboxController {

	@Autowired
	private InboxService service;

	@PostMapping("/send")
	public void sendMessage(@RequestBody Message message) {
		service.sendMessage(message);
	}
	
	@PutMapping("/{id}")
	public Message fireEvent(@RequestParam Long id) {
		return service.fireEvent(id);
	}

	@GetMapping("/all")
	public List<Message> getInbox() {
		return service.getInbox();
	}

	@GetMapping("/unread")
	public List<Message> unreadMessages() {
		return service.unreadMessages();
	}

}
