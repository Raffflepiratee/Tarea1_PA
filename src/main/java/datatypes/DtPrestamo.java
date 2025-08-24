package datatypes;

import java.util.Date;

public class DtPrestamo {
    private int idPrestamo;
    private Date fechaSoli;
    private EstadosP estadoPres;
    private Date fechaDev;
    
    public DtPrestamo(int idPrestamo, Date fechaSoli, EstadosP estadoPres, Date fechaDev) {
        this.idPrestamo = idPrestamo;
        this.fechaSoli = fechaSoli;
        this.estadoPres = estadoPres;
        this.fechaDev = fechaDev;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public Date getFechaSoli() {
        return fechaSoli;
    }

    public EstadosP getEstadoPres() {
        return estadoPres;
    }

    public Date getFechaDev() {
        return fechaDev;
    }

    @Override
    public String toString() {
        return "DtPrestamo [idPrestamo=" + idPrestamo + ", fechaSoli=" + fechaSoli + ", estadoPres=" + estadoPres
                + ", fechaDev=" + fechaDev + "]";
    }
}
