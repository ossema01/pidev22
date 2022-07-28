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

    private String subject = "Warning";

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
            String body = getBody(user);
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
            if (user.getLastLogin() != null && today.minusMonths(13).compareTo(user.getLastLogin()) == 0) {
                String body = getBody(user);
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

    public String getBody(User user) {
        return "<div style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;\">\n" +
                "    <div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: 'Lato', Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\"> We're thrilled to have you here! Get ready to dive into your new account.\n" +
                "    </div>\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "        <tr>\n" +
                "            <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "                    <tr>\n" +
                "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n" +
                "                            " + "<h2 style=\"font-size: 30px;font-weight:400;\">Hi, " + user.getFirstName() + " </h2>" +
                "<p style=\"margin: 0;\">We had not seen you in a while. In case you are not going to connect so soon we had to close your account.</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n" +
                "                            <p style=\"margin: 0;\">If you have any questions, just reply to this email&mdash;we're always happy to help out.</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n" +
                "                            <p style=\"margin: 0;\">Cheers,<br>PIDEV Team</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 30px 10px 0px 10px;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width: 600px;\">\n" +
                "                    <tr>\n" +
                "                        <td bgcolor=\"#FFECD1\" align=\"center\" style=\"padding: 30px 30px 30px 30px; border-radius: 4px 4px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\">\n" +
                "                            <h2 style=\"font-size: 20px; font-weight: 400; color: #111111; margin: 0;\">Need more help?</h2>\n" +
                "                            <p style=\"margin: 0;\"><a href=\"#\" target=\"_blank\" style=\"color: #FFA73B;\">We&rsquo;re here to help you out</a></p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</div>\n";
    }

}
