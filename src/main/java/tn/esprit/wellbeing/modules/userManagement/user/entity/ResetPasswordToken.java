package tn.esprit.wellbeing.modules.userManagement.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordToken {

    //10 mins
    private static final int EXPIRATION_TIME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_RESET_TOKEN"))
    private User user;

    public ResetPasswordToken(User user, String token) {
        super();
        this.user = user;
        this.expirationTime = calculateExpirationDate();
        this.token = token;
    }

    public ResetPasswordToken(String token) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationDate();
    }

    private Date calculateExpirationDate() {
        var calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, ResetPasswordToken.EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
