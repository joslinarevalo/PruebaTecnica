package com.example.pruebatecnica.servicio;

import com.example.pruebatecnica.dto.ProyectoDetalleValid;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ProyectoDetalleService {
    ResponseEntity<String> guardarDetalleCausa(ProyectoDetalleValid proyectoDetallle) throws IOException;
}
