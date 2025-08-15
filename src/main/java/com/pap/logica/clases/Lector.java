package com.pap.logica.clases;
import java.util.Date;
import com.pap.datatypes.EstadosU;
import com.pap.datatypes.Zonas;


public class Lector extends Usuario {
   private Date fechaIngreso;
   private EstadosU estadoUsuario;
   private Zonas zona;
   private String direccion;

   public Lector(String nombre, String correo, Date fechaIngreso, EstadosU estadoUsuario, Zonas zona, String direccion) {
       super(nombre, correo);
       this.fechaIngreso = fechaIngreso;
       this.estadoUsuario = estadoUsuario;
       this.zona = zona;
       this.direccion = direccion;
   }

    public Date getFechaIngreso() {
         return fechaIngreso;
    }

    public EstadosU getEstadoUsuario() {
        return estadoUsuario;
    }

    public Zonas getZona() {
        return zona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setEstadoUsuario(EstadosU estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public void setZona(Zonas zona) {
        this.zona = zona;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
