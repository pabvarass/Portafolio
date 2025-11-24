package com.skillnest.m6.sistemacapacitaciones.api;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InscripcionRequest {

    @NotNull
    @Positive
    private Long empleadoId;

    @NotNull
    @Positive
    private Long cursoId;

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}
