package tn.esprit.wellbeing.modules.notifications.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.wellbeing.modules.notifications.HasNotifications;
import tn.esprit.wellbeing.modules.notifications.NotificationException;
import tn.esprit.wellbeing.modules.notifications.data.Notification;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DefaultNotificationProviderTest {

	public static DefaultNotificationProvider INSTANCE = DefaultNotificationProvider.INSTANCE;

	@Test
	@Order(0)
	public void testGetInstance() {
		assertNotNull(INSTANCE.getInstance());
	}

	@Test
	@Order(1)
	public void testGetSubject() {
		assertEquals(HasNotifications.class, INSTANCE.getSubject());
	}

	@Test
	@Order(2)
	public void testGetNotification_withParams_HasNotifications_UserId_Message() {
		Notification notif = INSTANCE.getNotification(null, "hzerai", "Test Notif from Provider");
		assertEquals("hzerai", notif.getToUser());
		assertEquals("Test Notif from Provider", notif.getMessage());
	}

	@Test
	@Order(3)
	public void testGetNotification_withParams_HasNotifications_UserId() {
		assertThrows(NotificationException.class, () -> INSTANCE.getNotification(null, 1L));
	}

	@Test
	@Order(4)
	public void testGetNotification_withParams_UserId_Message() {
		Notification notif = INSTANCE.getNotification("hzerai", "Test Notif from Provider");
		assertEquals("hzerai", notif.getToUser());
		assertEquals("Test Notif from Provider", notif.getMessage());
	}

	@Test
	@Order(5)
	public void testGetNotification_withParams_HasNotifications_Message() {
		assertThrows(NotificationException.class,
				() -> INSTANCE.getNotification((HasNotifications) null, "Test Notif message"));
	}

}
