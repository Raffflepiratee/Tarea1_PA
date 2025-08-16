package com.pap.logica.clases;

import com.pap.logica.clases.Lector;
import com.pap.logica.clases.Bibliotecario;

public abstract class Usuario {
    private String nombre;
    private String correo;

    public Usuario(String nombre, String correo) {
        super();
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
