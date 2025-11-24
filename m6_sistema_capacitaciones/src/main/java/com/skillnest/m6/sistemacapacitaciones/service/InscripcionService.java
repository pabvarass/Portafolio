package com.skillnest.m6.sistemacapacitaciones.service;

import com.skillnest.m6.sistemacapacitaciones.model.Curso;
import com.skillnest.m6.sistemacapacitaciones.model.Empleado;
import com.skillnest.m6.sistemacapacitaciones.model.Inscripcion;
import com.skillnest.m6.sistemacapacitaciones.repository.CursoRepository;
import com.skillnest.m6.sistemacapacitaciones.repository.EmpleadoRepository;
import com.skillnest.m6.sistemacapacitaciones.repository.InscripcionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EmpleadoRepository empleadoRepository;
    private final CursoRepository cursoRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository,
                              EmpleadoRepository empleadoRepository,
                              CursoRepository cursoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.empleadoRepository = empleadoRepository;
        this.cursoRepository = cursoRepository;
    }

    /**
     * Usado por la API REST (/api/inscripciones).
     */
    public Inscripcion inscribirEmpleadoEnCurso(Long empleadoId, Long cursoId) {

        // 404 si el empleado no existe
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empleado no encontrado con id " + empleadoId
                ));

        // 404 si el curso no existe
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Curso no encontrado con id " + cursoId
                ));

        return crearInscripcion(empleado, curso);
    }

    /**
     * Usado por el flujo web (/empleado/cursos/inscribir/{id}).
     */
    public Inscripcion inscribirEmpleadoActualEnCurso(String username, Long cursoId) {

        Empleado empleado = empleadoRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empleado no encontrado con username " + username
                ));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Curso no encontrado con id " + cursoId
                ));

        return crearInscripcion(empleado, curso);
    }

    /**
     * Lógica común para crear la inscripción y actualizar cupos.
     */
    private Inscripcion crearInscripcion(Empleado empleado, Curso curso) {

        Integer cupos = curso.getCupos();
        if (cupos != null && cupos <= 0) {
            // 400 – sin cupos disponibles
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No hay cupos disponibles para este curso"
            );
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEmpleado(empleado);
        inscripcion.setCurso(curso);
        inscripcion.setFechaInscripcion(LocalDate.now());

        // Descontar 1 cupo si está definido
        if (cupos != null) {
            curso.setCupos(cupos - 1);
            cursoRepository.save(curso);
        }

        return inscripcionRepository.save(inscripcion);
    }
}
