package tn.esprit.wellbeing.modules.notifications.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationStatus;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

	@Autowired
	private NotificationService service;

	@PostMapping("/send")
	public void send(@RequestBody Notification notif) {
		service.sendNotification(notif);
	}

	@PutMapping("/{id}")
	public Notification fireEvent(@RequestParam Long id) {
		return service.fireEvent(id);
	}

	@GetMapping("/all")
	public List<Notification> getAllNotifications() {
		return service.getCurrentUserNotifications();
	}

	@GetMapping("/unread")
	public List<Notification> unreadMessages() {
		return service.findNotificationByUserIdAndStatus("hzerai", NotificationStatus.CREATED);
	}

}
