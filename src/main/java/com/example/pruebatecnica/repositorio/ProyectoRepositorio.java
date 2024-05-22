package com.example.pruebatecnica.repositorio;

import com.example.pruebatecnica.entidades.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProyectoRepositorio extends JpaRepository<Proyecto,Integer> {
    Proyecto findBynombre(String nombre);


}
