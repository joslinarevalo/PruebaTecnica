package com.example.pruebatecnica.servicesImpl;
import com.example.pruebatecnica.dto.ProyectoValid;
import com.example.pruebatecnica.dto.RespuestaGenerica;
import com.example.pruebatecnica.entidades.Proyecto;
import com.example.pruebatecnica.repositorio.ProyectoRepositorio;
import com.example.pruebatecnica.servicio.ProyectoService;
import com.example.pruebatecnica.servicio.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.google.gson.Gson;
@Service
public class ProyectoServiceImpl implements ProyectoService {
    @Autowired
    private ProyectoRepositorio proyectoRepositorio;
    Utils mensajes= new Utils();
    String Clase="Proyecto";
    @Override
    public ResponseEntity<String> guardarProyecto(String proyecto) throws IOException {
        String Accion="Registrar";
        HttpStatus http= HttpStatus.INTERNAL_SERVER_ERROR;
        RespuestaGenerica respuesta=new RespuestaGenerica("500","ERROR: Ha fallado la aplicación");
        Gson objeto= new Gson();
        ProyectoValid proyectoValid=objeto.fromJson(proyecto,ProyectoValid.class);
        Proyecto proyectoGuardar = new Proyecto();
        proyectoGuardar.setUuid((proyectoValid.getUUID()));
        proyectoGuardar.setNombre(proyectoValid.getNombre());
        proyectoGuardar.setFecha_creacion(proyectoValid.getFecha_creacion());
        proyectoGuardar.setEstado(proyectoValid.getEstado());
        Proyecto proyectRepetido= this.proyectoRepositorio.findBynombre(proyectoGuardar.getNombre());
        System.out.println(proyectoGuardar.getCodigoProyecto());
            if(proyectRepetido!=null) {
                http=HttpStatus.BAD_REQUEST;
                respuesta.setCodigo("400");
                respuesta.setMensaje("Error, El nombre del proyecto ya existe");
            }else {
                Proyecto proyectoCorrecto = this.proyectoRepositorio.save(proyectoGuardar);
                if (proyectoCorrecto != null) {
                    http = HttpStatus.CREATED;
                    respuesta.setCodigo("201");
                    respuesta.setMensaje(mensajes.Mensaje(201,Clase,Accion));
                }
            }
        String jsonResponses = objeto.toJson(respuesta);
        return new ResponseEntity<>(jsonResponses,http);
    }

    @Override
    public ResponseEntity<String> modificarProyecto( Proyecto proyecto) throws IOException {
        String Accion="Modificar";
        Gson objeto= new Gson();
        HttpStatus http= HttpStatus.INTERNAL_SERVER_ERROR;
        RespuestaGenerica respuesta=new RespuestaGenerica("500",mensajes.Mensaje(500,Clase,Accion));

        try {
            Proyecto proyetitoRepetido = this.proyectoRepositorio.findBynombre(proyecto.getNombre());

            if(proyetitoRepetido != null && proyecto.getCodigoProyecto() != proyetitoRepetido.getCodigoProyecto()){
                http=HttpStatus.BAD_REQUEST;
                respuesta.setCodigo("400");
                respuesta.setMensaje("Error, El nombre del proyecto ya existe");
            }else {

                Proyecto proyectoCorrecto = this.proyectoRepositorio.save(proyecto);
                if (proyectoCorrecto != null) {
                    http = HttpStatus.OK;
                    respuesta.setCodigo("200");
                    respuesta.setMensaje(mensajes.Mensaje(200,Clase,Accion));
                } else {
                    http = HttpStatus.BAD_REQUEST;
                    respuesta.setCodigo("400");
                    respuesta.setMensaje(mensajes.Mensaje(400,Clase,Accion));
                }
            }
        }catch (Exception e){
            http = HttpStatus.NOT_FOUND;
            respuesta.setCodigo("404");
            respuesta.setMensaje(mensajes.Mensaje(404,Clase,Accion));
        }
        String jsonResponse = objeto.toJson(respuesta);
        return new ResponseEntity<>(jsonResponse,http);
    }

    @Override
    public ResponseEntity<String> eliminarProyecto(int id) {
        String Accion="Eliminar";
        Gson objeto= new Gson();
        RespuestaGenerica resp =new RespuestaGenerica("500","Error al eliminar en server");
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            Optional<Proyecto> opt = Optional.ofNullable(this.proyectoRepositorio.findById(id).get());
            if(opt.isPresent()) {

                if(this.eliminarproyectoB(opt.get())) {
                    resp.setCodigo("200");
                    resp.setMensaje(mensajes.Mensaje(200,Clase,Accion));
                    http=HttpStatus.OK;
                }
                else{
                    resp.setCodigo("400");
                    resp.setMensaje("Error, no se puede eliminar el registro porque se esta usando en detalle de proyecto");
                    http=HttpStatus.BAD_REQUEST;
                }
            }
        }catch (Exception e){

            resp.setCodigo("400");
            resp.setMensaje("No se puede eliminar el proyecto, esta  relacionado.");
            http=HttpStatus.BAD_REQUEST;
        }
        String jsonResponse = objeto.toJson(resp);
        return new ResponseEntity<>(jsonResponse,http);
    }
    @Override
    public Boolean eliminarproyectoB(Proyecto proyecto) {
        try {
            this.proyectoRepositorio.delete(proyecto);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public ResponseEntity<List<Proyecto>> listaProyecto() {
        return null;
    }

}
