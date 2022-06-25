package tn.esprit.wellbeing.modules.notifications.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.wellbeing.modules.notifications.NotificationException;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.data.NotificationStatus;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotificationServiceTest {

	@Autowired
	private NotificationService service;

	@Test
	@Order(0)
	public void testSendNotification_withNullParams() {
		assertThrows(NotificationException.class, () -> service.sendNotification());
	}

	@Test
	@Order(1)
	public void testSendNotification_withNullUserId() {
		assertThrows(NotificationException.class, () -> service.sendNotification("Notification message example"));
	}

	@Test
	@Order(2)
	public void testSendNotification_withNullMessage() {
		assertThrows(NotificationException.class, () -> service.sendNotification(1L));
	}

	@Test
	@Order(3)
	public void testSendNotification_withUserIdAndMessage() {
		service.sendNotification("Notification message example", 1L);
	}

	@Test
	@Order(4)
	public void testFindNotificationByUserId() {
		List<Notification> notifications = service.findNotificationByUserId(1L);
		assertEquals(1, notifications.size());
		Notification currentNotif = notifications.iterator().next();
		assertEquals(1L, currentNotif.getUserId());
		assertEquals("Notification message example", currentNotif.getMessage());
	}

	@Test
	@Order(5)
	public void testFindNotificationByUserIdNotFound() {
		List<Notification> notifications = service.findNotificationByUserId(2L);
		assertEquals(0, notifications.size());
	}

	@Test
	@Order(6)
	public void testFindNotificationByUserIdAndStatus() {
		List<Notification> notifications = service.findNotificationByUserIdAndStatus(1L, NotificationStatus.CREATED);
		Notification currentNotif = notifications.iterator().next();
		assertEquals(1L, currentNotif.getUserId());
		assertEquals(NotificationStatus.CREATED, currentNotif.getStatus());
	}

	@Test
	@Order(7)
	public void testChangeStatus() {
		List<Notification> notifications = service.findNotificationByUserId(1L);
		Notification currentNotif = notifications.iterator().next();
		assertEquals(NotificationStatus.CREATED, currentNotif.getStatus());
		service.changeStatus(currentNotif);
		currentNotif = service.findNotificationByUserId(1L).iterator().next();
		assertEquals(NotificationStatus.SENT, currentNotif.getStatus());
	}

	@Test
	@Order(8)
	public void testForceChangeStatus() {
		List<Notification> notifications = service.findNotificationByUserId(1L);
		Notification currentNotif = notifications.iterator().next();
		assertEquals(NotificationStatus.SENT, currentNotif.getStatus());
		service.forceChangeStatus(currentNotif, NotificationStatus.CREATED);
		currentNotif = service.findNotificationByUserId(1L).iterator().next();
		assertEquals(NotificationStatus.CREATED, currentNotif.getStatus());
	}

}