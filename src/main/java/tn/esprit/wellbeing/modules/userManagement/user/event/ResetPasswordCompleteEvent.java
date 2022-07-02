package tn.esprit.wellbeing.modules.userManagement.user.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

@Getter
@Setter
public class ResetPasswordCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;

    public ResetPasswordCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}