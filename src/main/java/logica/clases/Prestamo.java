package logica.clases;
import java.util.Date;
import datatypes.EstadosP;
import javax.persistence.*;

@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrestamo;
    private Date fechaSol;
    private EstadosP estadoPres;
    private Date fechaDev;

    @ManyToOne
    private Lector lector;

    @ManyToOne
    private Material material;

    @ManyToOne
    private Bibliotecario bibliotecario;

    public Prestamo() {} 

    public Prestamo(int idPrestamo, Date fechaSol, EstadosP estadoPres, Date fechaDev) {
        this.idPrestamo = idPrestamo;
        this.fechaSol = fechaSol;
        this.estadoPres = estadoPres;
        this.fechaDev = fechaDev;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public Date getFechaSol() {
        return fechaSol;
    }

    public EstadosP getEstadoPres() {
        return estadoPres;
    }

    public Date getFechaDev() {
        return fechaDev;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public void setFechaSol(Date fechaSol) {
        this.fechaSol = fechaSol;
    }

    public void setEstadoPres(EstadosP estadoPres) {
        this.estadoPres = estadoPres;
    }

    public void setFechaDev(Date fechaDev) {
        this.fechaDev = fechaDev;
    }
}