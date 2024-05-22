package com.example.pruebatecnica.servicio;
import com.example.pruebatecnica.entidades.Proyecto;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.List;
public interface ProyectoService {

    ResponseEntity<String> guardarProyecto(String proyecto) throws IOException;

    ResponseEntity<String> modificarProyecto(Proyecto proyecto) throws IOException;

    ResponseEntity<String> eliminarProyecto(int id);

    ResponseEntity<List<Proyecto>> listaProyecto();

    Boolean eliminarproyectoB(Proyecto proyecto);

}
