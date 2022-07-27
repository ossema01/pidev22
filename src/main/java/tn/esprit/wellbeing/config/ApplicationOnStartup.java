package tn.esprit.wellbeing.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

    @Scheduled(cron = "@daily")
    void warnUser() {
        LocalDate today = LocalDate.now();
        List<User> users = userService.getUsers();
        for (User user : users) {
            if (today.minusMonths(12) == user.getLastLogin()) {

            }
        }
    }

    @Scheduled(cron = "@daily")
    void secondWarnUser() {
        LocalDate today = LocalDate.now();

        List<User> users = userService.getUsers();
        for (User user : users) {
            if (today.minusMonths(13) == user.getLastLogin()) {
                
            }
        }
    }

    @Scheduled(cron = "@daily")
    void archiveUser() {
        LocalDate today = LocalDate.now();

        List<User> users = userService.getUsers();
        for (User user : users) {
            if (today.minusMonths(14) == user.getLastLogin()) {
                user.setArchived(true);
                userService.saveUser(user);
            }
        }
    }

}
