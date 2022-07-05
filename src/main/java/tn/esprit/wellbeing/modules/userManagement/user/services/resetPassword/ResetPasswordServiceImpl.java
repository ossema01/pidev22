package tn.esprit.wellbeing.modules.userManagement.user.services.resetPassword;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.wellbeing.modules.userManagement.role.entity.Role;
import tn.esprit.wellbeing.modules.userManagement.role.repository.RoleRepository;
import tn.esprit.wellbeing.modules.userManagement.user.entity.ResetPasswordToken;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.entity.VerificationToken;
import tn.esprit.wellbeing.modules.userManagement.user.model.UserModel;
import tn.esprit.wellbeing.modules.userManagement.user.repository.ResetPasswordTokenRepository;
import tn.esprit.wellbeing.modules.userManagement.user.repository.UserRepository;
import tn.esprit.wellbeing.modules.userManagement.user.repository.VerificationTokenRepository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final ResetPasswordTokenRepository resetPasswordTokenRepository;


    @Override
    public ResetPasswordToken findByUserId(Long id) {
        return resetPasswordTokenRepository.findByUserId(id);
    }
}
