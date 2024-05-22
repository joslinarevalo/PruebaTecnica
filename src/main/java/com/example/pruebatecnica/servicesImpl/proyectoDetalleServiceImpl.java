package com.example.pruebatecnica.servicesImpl;

import com.example.pruebatecnica.dto.ProyectoDetalleValid;
import com.example.pruebatecnica.dto.RespuestaGenerica;
import com.example.pruebatecnica.entidades.Proyecto;
import com.example.pruebatecnica.entidades.ProyectoDetalle;
import com.example.pruebatecnica.repositorio.ProyectoDetalleRepositorio;
import com.example.pruebatecnica.servicio.ProyectoDetalleService;
import com.example.pruebatecnica.servicio.Utils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class proyectoDetalleServiceImpl implements ProyectoDetalleService {
    private final ProyectoDetalleRepositorio proyectoDetalleRepositorio;
    Utils mensajes= new Utils();
    String Clase="ProyectoDetalle";
    @Override
    public ResponseEntity<String> guardarDetalleCausa(ProyectoDetalleValid proyectoDetallle) throws IOException {
        String Accion="Registrar";
        Gson objeto= new Gson();
        HttpStatus http= HttpStatus.INTERNAL_SERVER_ERROR;
        RespuestaGenerica respuesta=new RespuestaGenerica("500",mensajes.Mensaje(500,Clase,Accion));

        // Validación de duplicados
        if (existeProyectoD(proyectoDetallle.getCodigoProyecto())) {
            respuesta.setCodigo("409");
            respuesta.setMensaje("El proyecto ya se agrego al detalle.");
            String jsonResponse = objeto.toJson(respuesta);
            return new ResponseEntity<>(jsonResponse,http);
        }
        try {

            if(proyectoDetallle!=null){
                ProyectoDetalle proyectoDetalleguardar=new ProyectoDetalle();
                proyectoDetalleguardar.setDescripcion(proyectoDetallle.getDescripcion());
                proyectoDetalleguardar.setArea(proyectoDetallle.getArea());
                proyectoDetalleguardar.setEstado(proyectoDetallle.getEstado());
                proyectoDetalleguardar.setProyect(new Proyecto(proyectoDetallle.getCodigoProyecto()));
                System.out.println("Estado antes de guardar: " + proyectoDetalleguardar.toString());
                ProyectoDetalle estado=proyectoDetalleRepositorio.save(proyectoDetalleguardar);
                System.out.println("Estado antes de guardar: " + estado.toString());
                if(estado!=null){
                    http = HttpStatus.CREATED;
                    respuesta.setCodigo("201");
                    respuesta.setMensaje(mensajes.Mensaje(201,Clase,Accion));
                }else{
                    http = HttpStatus.BAD_REQUEST;
                    respuesta.setCodigo("400");
                    respuesta.setMensaje(mensajes.Mensaje(400,Clase,Accion));
                }
            }
        }catch (Exception e){

            http = HttpStatus.NOT_FOUND;
            respuesta.setCodigo("404");
            respuesta.setMensaje("Error al registrar el Detalle de Causa ");

        }
        String jsonResponses = objeto.toJson(respuesta);
        return new ResponseEntity<>(jsonResponses,http);
    }
    private boolean existeProyectoD( int CodigoProyecto) {
        return proyectoDetalleRepositorio.existsBycodigoProyecto(CodigoProyecto);
    }
}
