package com.skillnest.m6.sistemacapacitaciones.api;

import com.skillnest.m6.sistemacapacitaciones.model.Curso;
import com.skillnest.m6.sistemacapacitaciones.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin
public class CursoRestController {

    private final CursoService cursoService;

    public CursoRestController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarTodos();
    }
}
