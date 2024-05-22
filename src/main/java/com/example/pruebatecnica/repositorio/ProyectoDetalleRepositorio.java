package com.example.pruebatecnica.repositorio;

import com.example.pruebatecnica.entidades.Proyecto;
import com.example.pruebatecnica.entidades.ProyectoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProyectoDetalleRepositorio extends JpaRepository<ProyectoDetalle,Integer> {
    boolean existsBycodigoProyecto(int codigoProyecto);
}
