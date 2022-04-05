package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String getIndex(Principal principal, Model model) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("people", userService.getAllUsers());
        return "user";
    }

}
