package com.skillnest.m6.sistemacapacitaciones.repository;

import com.skillnest.m6.sistemacapacitaciones.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
