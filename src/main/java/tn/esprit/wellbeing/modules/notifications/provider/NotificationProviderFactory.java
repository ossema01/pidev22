package tn.esprit.wellbeing.modules.notifications.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import tn.esprit.wellbeing.modules.notifications.HasNotifications;
import tn.esprit.wellbeing.modules.notifications.NotificationProvider;

public class NotificationProviderFactory {

	private static volatile Map<Class<? extends HasNotifications>, NotificationProvider> providersByClass = new ConcurrentHashMap<>();

	public static NotificationProvider getNotificationProvider(HasNotifications entity) {
		return providersByClass.get(entity.getClass());
	}

	public static boolean hasProvider(HasNotifications entity) {
		return providersByClass.containsKey(entity.getClass());
	}

	public static void registerNotificationProvider(NotificationProvider notificationProviderInstance) {
		providersByClass.put(notificationProviderInstance.getSubject(), notificationProviderInstance);
	}

	public static NotificationProvider getDefaultProvider() {
		return DefaultNotificationProvider.INSTANCE;
	}

}
