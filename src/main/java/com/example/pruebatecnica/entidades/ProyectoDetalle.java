package com.example.pruebatecnica.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="proyectoDetalle")
public class ProyectoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CodigoProyectoDetalle;
    private String descripcion;
    private Number area;
    private Boolean estado;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="CodigoProyecto")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Proyecto proyect;
    public ProyectoDetalle() {

    }
}
