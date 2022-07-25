package tn.esprit.wellbeing.modules.userManagement.role.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.wellbeing.modules.userManagement.role.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String roleName);
}
