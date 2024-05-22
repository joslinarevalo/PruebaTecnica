package com.example.pruebatecnica.controladores;
import com.example.pruebatecnica.entidades.Proyecto;
import com.example.pruebatecnica.servicio.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins ="http://localhost:4200" , maxAge = 3600)
@RestController
@RequestMapping("/api/proyecto")
@RequiredArgsConstructor
public class ProyectoControlador {
    private final ProyectoService proyectoService;

    @GetMapping("listar")
    public ResponseEntity<List<Proyecto>> listarProyecto(){
        return this.proyectoService.listaProyecto();
    }
    @PostMapping("guardar")
    public ResponseEntity<String> guardarProyecto(@RequestParam("proyecto") String proyecto) throws IOException {
        return this.proyectoService.guardarProyecto(proyecto);
    }

    @PutMapping("modificar")
    public ResponseEntity<String> modificarProyecto(@RequestBody Proyecto proyecto) throws IOException {

        return this.proyectoService.modificarProyecto(proyecto);

    }
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarTipoCausa(@PathVariable("id") int id) throws IOException {
        return this.proyectoService.eliminarProyecto(id);
    }

}
