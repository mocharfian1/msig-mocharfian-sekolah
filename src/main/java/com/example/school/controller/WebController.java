package com.example.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "student-dashboard";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("appName", appName);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("appName", appName);
        return "login";
    }

    @GetMapping("/admin")
    public String loginAdminPage(Model model) {
        model.addAttribute("appName", appName);
        return "admin";
    }
}
