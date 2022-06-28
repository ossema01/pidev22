package tn.esprit.wellbeing.modules.user.services;

import tn.esprit.wellbeing.modules.role.entity.Role;
import tn.esprit.wellbeing.modules.user.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    User getUser(String userName);

    List<User> getUsers();
}
