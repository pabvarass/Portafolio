package com.skillnest.m6.sistemacapacitaciones.controller;

import com.skillnest.m6.sistemacapacitaciones.model.Curso;
import com.skillnest.m6.sistemacapacitaciones.model.Instructor;
import com.skillnest.m6.sistemacapacitaciones.repository.InstructorRepository;
import com.skillnest.m6.sistemacapacitaciones.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/cursos")
public class AdminCursoController {

    private final CursoService cursoService;
    private final InstructorRepository instructorRepository;

    public AdminCursoController(CursoService cursoService,
                                InstructorRepository instructorRepository) {
        this.cursoService = cursoService;
        this.instructorRepository = instructorRepository;
    }

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "admin/cursos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("instructores", instructorRepository.findAll());
        return "admin/cursos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable("id") Long id, Model model) {
        Curso curso = cursoService.buscarPorId(id);
        model.addAttribute("curso", curso);
        model.addAttribute("instructores", instructorRepository.findAll());
        return "admin/cursos/formulario";
    }

    @PostMapping
    public String guardarCurso(@ModelAttribute("curso") Curso curso,
                               @RequestParam("instructorId") Long instructorId) {

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("Instructor no encontrado"));

        curso.setInstructor(instructor);
        cursoService.guardar(curso);
        return "redirect:/admin/cursos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable("id") Long id) {
        cursoService.eliminar(id);
        return "redirect:/admin/cursos";
    }
}
