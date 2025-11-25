package com.skillnest.m6.sistemacapacitaciones.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // Muestra templates/login.html
        return "login";
    }
}
