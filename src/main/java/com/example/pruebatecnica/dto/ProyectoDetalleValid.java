package com.example.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProyectoDetalleValid {
    private int CodigoProyectoDetalle;
    @NotBlank(message = "La descripcion no puede quedar vacia ")
    private String descripcion;
    @NotBlank(message = "El Area no puede quedar vacia ")
    private Number area;
    @NotBlank(message = "Marque un estado ")
    private Boolean estado;
    @NotBlank(message = "Seleccione un proyecto")
    private int CodigoProyecto;
}
