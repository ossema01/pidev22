package tn.esprit.wellbeing.modules.userManagement.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;
import tn.esprit.wellbeing.modules.userManagement.user.model.LoginRequest;
import tn.esprit.wellbeing.modules.userManagement.user.model.LoginResponse;
import tn.esprit.wellbeing.modules.userManagement.user.services.UserService;
import tn.esprit.wellbeing.security.TokenProvider;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider jwtTokenUtil;



    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {

        User user = (User) userService.loadUserByUsername(request.getUsername());
        log.info("User :: {}", user);

        if (!userService.matchesPassword(request.getPassword(), user.getPassword())) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }


        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        userService.updateMonthlyActive(request.getUsername());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping(value = "/currentUser")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

}
