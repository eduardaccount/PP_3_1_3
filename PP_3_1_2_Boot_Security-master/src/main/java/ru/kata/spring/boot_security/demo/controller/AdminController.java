package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "adminIndex";
    }

    @GetMapping("/addingUser")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        return "addingUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        System.out.println(user);
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/editPage")
    public String editPage(@RequestParam(value = "id") String id, Model model) {
        User user = userService.getUserById(Long.parseLong(id));
        model.addAttribute("current_user", user);
        return "editUser";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") String id) {
        userService.deleteUser(Long.parseLong(id));
        return "redirect:/";
    }
}