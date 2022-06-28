package tn.esprit.wellbeing.modules.user.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.wellbeing.modules.role.entity.Role;
import tn.esprit.wellbeing.modules.role.repository.RoleRepository;
import tn.esprit.wellbeing.modules.user.entity.User;
import tn.esprit.wellbeing.modules.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role {} to user {}", roleName, userName);
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
}
