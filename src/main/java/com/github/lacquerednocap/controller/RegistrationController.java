package com.github.lacquerednocap.controller;

import com.github.lacquerednocap.entity.Role;
import com.github.lacquerednocap.entity.User;
import com.github.lacquerednocap.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class RegistrationController {

    private final UserRepository userRepo;

    public RegistrationController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public  String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "user exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
