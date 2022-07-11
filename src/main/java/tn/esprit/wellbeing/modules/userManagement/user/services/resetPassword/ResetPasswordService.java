package tn.esprit.wellbeing.modules.userManagement.user.services.resetPassword;

import tn.esprit.wellbeing.modules.userManagement.user.entity.ResetPasswordToken;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.entity.VerificationToken;

public interface ResetPasswordService {
    ResetPasswordToken findByUserId(Long id);

    ResetPasswordToken findByToken(String token);

    ResetPasswordToken save(ResetPasswordToken resetPasswordToken);

    void deleteById(Long id);

}
