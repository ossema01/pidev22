package tn.esprit.wellbeing.modules.userManagement.user.services.verificationToken;

import tn.esprit.wellbeing.modules.userManagement.user.entity.ResetPasswordToken;
import tn.esprit.wellbeing.modules.userManagement.user.entity.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);

    VerificationToken findByUserId(Long id);

    VerificationToken save(VerificationToken verificationToken);

    void deleteById(Long id);
}
