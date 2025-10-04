package datatypes;

import java.util.Date;

public class DtMaterial {

    private int idMaterial;
    private Date fechaRegistro;

    // Mostrar informacion
    public DtMaterial(int idMaterial, Date fechaRegistro) {
        this.idMaterial = idMaterial;
        this.fechaRegistro = fechaRegistro;
    }

    // Registrar nuevo
    public DtMaterial(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return "DtMaterial [idMaterial=" + idMaterial + ", fechaRegistro=" + fechaRegistro + "]";
    }

}
