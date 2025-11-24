package com.skillnest.m6.sistemacapacitaciones.controller;

import com.skillnest.m6.sistemacapacitaciones.service.CursoService;
import com.skillnest.m6.sistemacapacitaciones.service.InscripcionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/empleado/cursos")
public class EmpleadoCursoController {

    private final CursoService cursoService;
    private final InscripcionService inscripcionService;

    public EmpleadoCursoController(CursoService cursoService,
                                   InscripcionService inscripcionService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    public String listarCursosDisponibles(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "empleado/cursos/lista";
    }

    @PostMapping("/inscribir/{cursoId}")
    public String inscribirEnCurso(@PathVariable Long cursoId,
                                   Principal principal) {
        try {
            inscripcionService.inscribirEmpleadoActualEnCurso(principal.getName(), cursoId);
            return "redirect:/empleado/cursos?inscrito";
        } catch (IllegalStateException e) {
            // Sin cupos disponibles
            return "redirect:/empleado/cursos?sinCupos";
        }
    }
}
