package com.skillnest.m6.sistemacapacitaciones;

import com.skillnest.m6.sistemacapacitaciones.model.Curso;
import com.skillnest.m6.sistemacapacitaciones.model.Empleado;
import com.skillnest.m6.sistemacapacitaciones.model.Instructor;
import com.skillnest.m6.sistemacapacitaciones.repository.CursoRepository;
import com.skillnest.m6.sistemacapacitaciones.repository.EmpleadoRepository;
import com.skillnest.m6.sistemacapacitaciones.repository.InstructorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(InstructorRepository instructorRepository,
                               EmpleadoRepository empleadoRepository,
                               CursoRepository cursoRepository) {
        return args -> {

            Instructor inst = new Instructor();
            inst.setNombre("Juan Pérez");
            inst.setEmail("juan.perez@empresa.cl");
            instructorRepository.save(inst);

            Curso curso = new Curso();
            curso.setNombre("Introducción a Spring Boot");
            curso.setDescripcion("Curso básico de desarrollo con Spring Boot");
            curso.setCupos(25);
            curso.setInstructor(inst);
            cursoRepository.save(curso);

            Empleado emp = new Empleado();
            emp.setNombre("Empleado Demo");
            emp.setEmail("empleado@empresa.cl");
            emp.setDepartamento("TI");
            emp.setUsername("empleado");
            empleadoRepository.save(emp);
        };
    }
}
