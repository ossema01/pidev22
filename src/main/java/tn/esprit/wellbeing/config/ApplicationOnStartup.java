package tn.esprit.wellbeing.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.wellbeing.modules.notifications.NotificationService;
import tn.esprit.wellbeing.modules.notifications.data.Notification;
import tn.esprit.wellbeing.modules.notifications.provider.NotificationProviderFactory;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;

import java.time.LocalDate;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class ApplicationOnStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    private String subject = "";

    private String body = "";

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

    }

    //    @Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "@monthly")
    void monthlyActiveUser() {
        List<User> users = userService.getUsers();
        for (User user : users) {
            user.setMonthlyActive(0);
            userService.saveUser(user);
        }
    }

    @Scheduled(cron = "0 * * * * *")
    void warnUser() {
        LocalDate today = LocalDate.now();
        List<User> users = userService.getUsers();
        for (User user : users) {
            log.info("schedule {}, {}, {}", today.minusMonths(12).compareTo(user.getLastLogin()), today.minusMonths(12), user.getLastLogin());
            if (user.getLastLogin() != null && today.minusMonths(12).compareTo(user.getLastLogin()) == 0) {
                Notification notif = NotificationProviderFactory.getDefaultProvider().getEmailNotification(user.getUsername(), subject, body);
                notificationService.sendSystemNotification(user.getEmail(), notif);
            }
        }
    }

    @Scheduled(cron = "0 * * * * *")
    void secondWarnUser() {
        LocalDate today = LocalDate.now();

        List<User> users = userService.getUsers();
        for (User user : users) {
            log.info("schedule {}, {}, {}", today.minusMonths(13).compareTo(user.getLastLogin()), today.minusMonths(13), user.getLastLogin());

            if (user.getLastLogin() != null && today.minusMonths(13).compareTo(user.getLastLogin()) == 0) {
                Notification notif = NotificationProviderFactory.getDefaultProvider().getEmailNotification(user.getUsername(), subject, body);
                notificationService.sendSystemNotification(user.getEmail(), notif);
            }
        }
    }

    @Scheduled(cron = "@daily")
    void archiveUser() {
        LocalDate today = LocalDate.now();

        List<User> users = userService.getUsers();
        for (User user : users) {
            if (user.getLastLogin() != null && today.minusMonths(14).compareTo(user.getLastLogin()) == 0) {
                user.setArchived(true);
                userService.saveUser(user);
            }
        }
    }

}
