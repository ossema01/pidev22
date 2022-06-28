package tn.esprit.wellbeing.modules.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.wellbeing.modules.user.entity.User;
import tn.esprit.wellbeing.modules.user.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @GetMapping("/searchAll")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<User> createtUser(@RequestBody User user) {
        return ResponseEntity.created(null).body(userService.saveUser(user));
    }
}
