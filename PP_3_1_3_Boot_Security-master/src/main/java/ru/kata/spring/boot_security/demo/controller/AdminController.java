package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String getIndex(Principal principal, Model model) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("people", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("newUser") User user) {
        System.out.println(user);
        System.out.println(userService.addUser(new User(user.getUserName(), user.getUserPassword(), user.getUserEmail(), user.getUserAge(), user.getRoles())));
        return "redirect:/admin/index";
    }

    @PostMapping("/{id}")
    public String editUser(@PathVariable long id, @ModelAttribute User user) {
        user.setUserId(id);
        userService.updateUser(user);
        return "redirect:/admin/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(Long.parseLong(id));
        return "redirect:/admin/index";
    }
}