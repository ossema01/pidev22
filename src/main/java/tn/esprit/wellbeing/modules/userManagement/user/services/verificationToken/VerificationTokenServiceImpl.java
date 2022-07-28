package tn.esprit.wellbeing.modules.userManagement.user.services.verificationToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.wellbeing.modules.userManagement.user.entity.VerificationToken;
import tn.esprit.wellbeing.modules.userManagement.user.repository.VerificationTokenRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;


    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public VerificationToken findByUserId(Long id) {
        return verificationTokenRepository.findByUserId(id);
    }

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void deleteById(Long id) {
        verificationTokenRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(Long id) {

    }
}
