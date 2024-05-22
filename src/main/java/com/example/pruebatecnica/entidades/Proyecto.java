package com.example.pruebatecnica.entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table( name="proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoProyecto;
    private String uuid;
    private String nombre;
    private Timestamp fecha_creacion;
    private Boolean estado;
    @OneToMany(mappedBy = "proyect", cascade = CascadeType.ALL)
    private Set<ProyectoDetalle> proyectDetalle= new HashSet<>();

    public Proyecto (int codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public Proyecto() {

    }

    public void setProyecto (Set<ProyectoDetalle>protecD){
        this.proyectDetalle=protecD;
        for(ProyectoDetalle proyectoDetallito : proyectDetalle)
        {
            proyectoDetallito.setProyect(this);
        }
    }

}
