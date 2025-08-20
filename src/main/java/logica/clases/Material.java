package com.pap.logica.clases;
import java.util.Date;

public abstract class Material {
    private int idMaterial;
    private Date fechaRegistro;

    public Material(int idMaterial, Date fechaRegistro) {
        super();
        this.idMaterial = idMaterial;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
