package com.example.pruebatecnica.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class ProyectoValid {
    private int codigoProyecto;
    @NotBlank(message = "Este campo es requerido")
    @NotNull(message = "UUID del proyecto no puede ser nulo")
    @Size(min=4, max = 100,message = "el UUID debe tener como minimo 4 caracteres")
    private String UUID;
    @NotBlank(message = "Este campo es requerido")
    @NotNull(message = "El nombre del proyecto no puede ser nulo")
    @Size(min=4, max = 150,message = "el UUID debe tener como minimo 4 caracteres")
    private String nombre;
    @NotBlank(message = "Este campo es requerido")
    @NotNull(message = "UUID del proyecto no puede ser nulo")
    private Timestamp fecha_creacion;
    @NotBlank(message = "Este campo es requerido")
    @NotNull(message = "Seleccione un estado")
    private Boolean estado;

}
