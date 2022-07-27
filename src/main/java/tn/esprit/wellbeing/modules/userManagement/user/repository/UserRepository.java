package tn.esprit.wellbeing.modules.userManagement.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String userName);

    User findByEmail(String email);
    
    User findUserById(Long id);

    User[] findAllByOrderByMonthlyActiveDesc();

    void deleteByUsername(String username);
}
