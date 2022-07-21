package tn.esprit.wellbeing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.services.*;

import java.util.List;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enable", matchIfMissing = true)
public class ApplicationOnStartup implements ApplicationListener<ApplicationReadyEvent> {

//    private UserService userService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

    }

    @Scheduled(cron = "@monthly")
    void monthlyActiveUser() {
//        List<User> users = userService.getUsers();
//        for (User user : users) {
//            user.setMonthlyActive(0);
//        }
    }

    @Scheduled(cron = "@daily")
    void lockUser() {
        System.out.println("jobbbbbb after 2s");
    }

}
