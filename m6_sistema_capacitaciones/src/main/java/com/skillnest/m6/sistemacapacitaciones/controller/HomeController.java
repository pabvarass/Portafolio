package com.skillnest.m6.sistemacapacitaciones.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {

        // Si no está logueado, mándalo al login
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        // Si es ADMIN -> panel de admin
        boolean esAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (esAdmin) {
            return "redirect:/admin/cursos";
        }

        // Si no es ADMIN, asumimos EMPLEADO
        return "redirect:/empleado/cursos";
    }
}
