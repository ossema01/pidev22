package tn.esprit.wellbeing.modules.userManagement.user.services.resetPassword;

import tn.esprit.wellbeing.modules.userManagement.user.entity.ResetPasswordToken;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

public interface ResetPasswordService {
    ResetPasswordToken findByUserId(Long id);
}
