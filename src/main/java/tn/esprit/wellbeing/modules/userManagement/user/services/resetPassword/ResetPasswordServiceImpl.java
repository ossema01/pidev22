package tn.esprit.wellbeing.modules.userManagement.user.services.resetPassword;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.wellbeing.modules.userManagement.user.entity.ResetPasswordToken;
import tn.esprit.wellbeing.modules.userManagement.user.repository.ResetPasswordTokenRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final ResetPasswordTokenRepository resetPasswordTokenRepository;


    @Override
    public ResetPasswordToken findByUserId(Long id) {
        return resetPasswordTokenRepository.findByUserId(id);
    }

    @Override
    public ResetPasswordToken findByToken(String token) {
        return resetPasswordTokenRepository.findByToken(token);
    }


    @Override
    public ResetPasswordToken save(ResetPasswordToken resetPasswordToken) {
        return resetPasswordTokenRepository.save(resetPasswordToken);
    }

    @Override
    public void deleteById(Long id) {
        resetPasswordTokenRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(Long id) {
        resetPasswordTokenRepository.deleteByUserId(id);
    }
}
