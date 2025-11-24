package com.skillnest.m6.sistemacapacitaciones.api;

import com.skillnest.m6.sistemacapacitaciones.model.Inscripcion;
import com.skillnest.m6.sistemacapacitaciones.service.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin
public class InscripcionRestController {

    private final InscripcionService inscripcionService;

    public InscripcionRestController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "inscripciones-api-ok";
    }

    @PostMapping
    public Inscripcion crearInscripcion(@Valid @RequestBody InscripcionRequest request) {
        return inscripcionService.inscribirEmpleadoEnCurso(
                request.getEmpleadoId(),
                request.getCursoId()
        );
    }
}
