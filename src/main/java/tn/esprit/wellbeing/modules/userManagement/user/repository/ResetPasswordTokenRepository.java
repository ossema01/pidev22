package tn.esprit.wellbeing.modules.userManagement.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.wellbeing.modules.userManagement.user.entity.ResetPasswordToken;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByToken(String token);

    ResetPasswordToken findByUserId(Long id);

    void deleteByUserId(Long id);

}
