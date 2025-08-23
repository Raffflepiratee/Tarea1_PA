package datatypes;

public class DtPrestamo {
    private int idPrestamo;
    private Date fechaSoli;
    private EstadoP estadoPres;
    private Date fechaDev;
    
    public DtPrestamo(int idPrestamo, Date fechaSoli, EstadoP estadoPres, Date fechaDev) {
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

    public EstadoP getEstadoPres() {
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
